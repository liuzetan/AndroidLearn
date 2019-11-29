package com.lzt.fix.plugin

import com.android.build.api.transform.Context
import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.Format
import com.android.build.api.transform.JarInput
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformOutputProvider
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils
import javassist.ClassPool
import javassist.CtClass
import javassist.CtMethod
import org.apache.commons.codec.digest.DigestUtils
import org.gradle.api.Project

//只需要继承Transform类即可
class LifeCycleTransform extends Transform {

    Project project

    LifeCycleTransform(Project project) {
        this.project = project
    }

    //该Transform的名称，自定义即可，只是一个标识
    @Override
    String getName() {
        return "LifeCycleTransform"
    }

    //该Transform支持扫描的文件类型，分为class文件和资源文件，我们这里只处理class文件的扫描
    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    //Transfrom的扫描范围，我这里扫描整个工程，包括当前module以及其他jar包、aar文件等所有的class
    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    //是否增量扫描
    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(Context context, Collection<TransformInput> inputs, Collection<TransformInput> referencedInputs,
                   TransformOutputProvider outputProvider, boolean isIncremental) throws IOException, TransformException, InterruptedException {
        println "\nstart to transform-------------->>>>>>>"
        outputProvider.deleteAll()
        inputs.each {TransformInput input ->
            input.directoryInputs.each {
                DirectoryInput directoryInput ->
                    injectCode(directoryInput.file.absolutePath, project)
                    def dest = outputProvider.getContentLocation(directoryInput.name, directoryInput.contentTypes,
                    directoryInput.scopes, Format.DIRECTORY)
                    println("" + directoryInput.file + " transform " + dest)
                    FileUtils.copyDirectory(directoryInput.file, dest)
            }

            input.jarInputs.each { JarInput jarInput ->
                def jarName = jarInput.name
                def md5Name = DigestUtils.md5Hex(jarInput.file.getAbsolutePath())
                if (jarName.endsWith(".jar")) {
                    jarName = jarName.substring(0, jarName.length() - 4)
                }
                def dest = outputProvider.getContentLocation(jarName + md5Name, jarInput.contentTypes, jarInput.scopes, Format.JAR)
//                println("jar " + jarInput.file + " transform " + dest)
                FileUtils.copyFile(jarInput.file, dest)
            }
        }
        println "transform finish----------------<<<<<<<\n"
    }

    ClassPool pool = ClassPool.getDefault()
    void injectCode(String path, Project project) {
        pool.appendClassPath(path)
        pool.appendClassPath(project.android.bootClasspath[0].toString())
        println("path = " + path)
        println("project.android.bootClasspath[0].toString() = " + project.android.bootClasspath[0].toString())
//        pool.importPackage("android.os.Bundle")

        File dir = new File(path)

        if (dir.isDirectory()) {
            dir.eachFileRecurse {File file ->
                String filePath = file.absolutePath
//                println("filePath = ${filePath}")
                if ("MainActivity.class".equals(file.getName())) {
                    CtClass ctClass = pool.getCtClass("com.example.testing.androidlearn.MainActivity")
                    println("ctClass = ${ctClass}")
                    if (ctClass.isFrozen())
                        ctClass.defrost()
                    CtMethod ctMethod = ctClass.getDeclaredMethod("onCreate")
                    String insetBeforeStr = """ android.widget.Toast.makeText(this,"abc 我是被插入的Toast代码~!!",android.widget.Toast.LENGTH_SHORT).show();
                           """
                    ctMethod.insertBefore(insetBeforeStr)
                    ctClass.writeFile(path)
                    ctClass.detach()
                }
            }
        }
    }
}

