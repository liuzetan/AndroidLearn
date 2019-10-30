package com.lzt.fix.plugin

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

public class BytecodeFixPlugin implements Plugin<Project> {
    static final String GROUP = 'LintCleaner'
    static final String EXTENSION_NAME = 'lintCleaner'

    @Override
    void apply(Project project) {
        println("This is a gradle plugin, (*^__^*)， name = ${project.name}")
// 获取外部参数
        project.extensions.create(EXTENSION_NAME, PluginExtension, project)

        // 创建清理任务
        Task cleanTask = project.tasks.create(CleanTask.NAME, CleanTask)

        // 执行完lint后，再执行
        cleanTask.dependsOn(project.tasks.getByName('lint'))

        println "------LifeCycle plugin entrance-------"
        def android = project.extensions.getByType(AppExtension)
        android.registerTransform(new LifeCycleTransform(project))
    }
}