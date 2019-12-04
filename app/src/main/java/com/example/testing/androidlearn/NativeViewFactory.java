package com.example.testing.androidlearn;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import io.flutter.plugin.common.MessageCodec;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugin.platform.PlatformViewFactory;

public class NativeViewFactory extends PlatformViewFactory {


    public NativeViewFactory(MessageCodec<Object> createArgsCodec) {
        super(createArgsCodec);
    }

    @Override
    public PlatformView create(Context context, int i, Object o) {
        return new PlatformView() {
            @Override
            public View getView() {
                TextView textView = new TextView(context);
                textView.setText("I am a native text view");
                return textView;
            }

            @Override
            public void dispose() {

            }
        };
    }

    public static void registerWith(PluginRegistry registry) {
        final String key = "nativeTestView";
        if (registry.hasPlugin(key)) return;
        PluginRegistry.Registrar registrar = registry.registrarFor(key);
        registrar.platformViewRegistry().registerViewFactory(key, new NativeViewFactory(new StandardMessageCodec()));
    }
}
