import 'package:flutter/material.dart';
import 'package:fluro/fluro.dart';
import 'dart:ui';

import 'package:flutter/services.dart';
import 'package:my_flutter/routerManager.dart';

//void main() => runApp(new MyApp());
void main() => runApp(_widgetForRoute(window.defaultRouteName));

Widget _widgetForRoute(String route) {
    switch (route) {
        case 'route1':
            return new MyApp();
        case 'route2':
            return new MyApp();
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

    void _incrementCounter() {
        setState(() {
            _counter++;
        });
    }
    
    void _goNext() async{
        var body = '{"k1": "kkkkkkk","k2": "llllllll"}';
        RouteManager.router.navigateTo(context, '/test/$body', transition: TransitionType.inFromRight);
    
//        String batteryLevel;
//        try {
//            final int result = await platform.invokeMethod('getRandom');
//            setState(() {
//              _counter = result;
//            });
//            batteryLevel = 'Battery level at $result % .';
//        } on PlatformException catch (e) {
//            batteryLevel = "Failed to get battery level: '${e.message}'.";
//        }
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
