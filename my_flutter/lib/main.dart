import 'dart:async';

import 'package:flutter/material.dart';
import 'package:fluro/fluro.dart';
import 'dart:ui';

import 'package:flutter/services.dart';
import 'package:my_flutter/page/HomePage.dart';
import 'package:my_flutter/routerManager.dart';
import 'package:my_flutter/test.dart';

//void main() => runApp(new MyApp());
void main() => runApp(_widgetForRoute(window.defaultRouteName));

Widget _widgetForRoute(String route) {
    switch (route) {
        case 'route1':
            return new MyApp();
        case 'route2':
            return new Test('{"k1": "kkkkkkk","k2": "llllllll"}');
        default:
            return Center(
                child: Text('Unknown    route1 : $route',
                        textDirection: TextDirection.ltr),
            );
    }
}

class MyApp extends StatelessWidget {
    // This widget is the root of your application.

    MyApp() {
        RouteManager.defineRoutes();
    }
    @override
    Widget build(BuildContext context) {
        return new MaterialApp(
            title: 'Flutter Demo',
            theme: new ThemeData(
                primarySwatch: Colors.blue,
            ),
            home: new MyHomePage(title: 'Flutter Demo Home Page'),
        );
    }
}

class MyHomePage extends StatefulWidget {
    MyHomePage({Key key, this.title}) : super(key: key);
    final String title;

    @override
    _MyHomePageState createState() => new _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
    static const platform = const MethodChannel('samples.flutter.io/abc');
    int _counter = 0;
    
//    static const eventPlugin = const EventChannel("com.xxx");
    static const BasicMessageChannel<String> basicMessageChannel = const BasicMessageChannel("CHANNEL", StringCodec());
    StreamSubscription _streamSubscription;
    @override
    void initState() {
        super.initState();
        platform.setMethodCallHandler(platformCallHandler);
//        _streamSubscription = eventPlugin.receiveBroadcastStream().listen(_onData, onError: _onError, cancelOnError: true);
        basicMessageChannel.setMessageHandler(_handleBasic);
    }

    Future<dynamic> platformCallHandler(MethodCall call) async {
        switch (call.method) {
            case "getInputParams":
                return "arguments";
            default:
                return "ddddffffaaauuulllttt";
        }
    }

    Future<String> _handleBasic(String message) async {
        print("_handleBasic message = " + message);
        // 发送一个空消息
        return "_emptyMessage";
    }


//    void _onData(event) {
//        print("onData event = " + event);
//    }
//
//
//    void _onError(event) {
//        print("onError event = " + event);
//    }

    @override
    void dispose() {
        super.dispose();
        if(_streamSubscription != null) {
            _streamSubscription.cancel();
        }
    }

    void _incrementCounter() {
        setState(() {
            _counter++;
        });
    }
    
    void _goNext() async{
//        var body = '{"k1": "kkkkkkk","k2": "llllllll"}';
//        RouteManager.router.navigateTo(context, '/test/$body', transition: TransitionType.inFromRight);
    
        String batteryLevel;
        try {
            final int result = await platform.invokeMethod('getRandom');
            setState(() {
              _counter = result;
            });
            batteryLevel = 'Battery level at $result % .';
        } on PlatformException catch (e) {
            batteryLevel = "Failed to get battery level: '${e.message}'.";
        }
        basicMessageChannel.send("what is it");
    }

    @override
    Widget build(BuildContext context) {
        return new Scaffold(
            appBar: new AppBar(
                title: new Text(widget.title),
            ),
            body: new Center(
                child: new Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: <Widget>[
                        Container(
                        child: AndroidView(viewType: "nativeTestView", creationParamsCodec: const StandardMessageCodec(),
                        creationParams: {'method_layout_size': 70},),
                            color: Colors.blue, height: 40,
                        ),
                        new Text(
                            'You have pushed the button this many times:',
                        ),
                        new Text(
                            '$_counter',
                            style: Theme
                                    .of(context)
                                    .textTheme
                                    .display1,
                        ),
                        new RaisedButton(onPressed: _goNext,
                        child: new Text("按钮1"),)
                    ],
                ),
            ),
            floatingActionButton: new FloatingActionButton(
                onPressed: _incrementCounter,
                tooltip: 'Increment',
                child: new Icon(Icons.add),
            ), // This trailing comma makes auto-formatting nicer for build methods.
        );
    }

}
