import 'package:flutter/material.dart';
import 'dart:convert';
import 'package:flutter/services.dart';
import 'package:my_flutter/HttpUtil.dart';
import 'package:my_flutter/routerManager.dart';


class Test extends StatelessWidget {
    // This widget is the root of your application.
    String k1;
    Test(this.k1);
    @override
    Widget build(BuildContext context) {
        final parsed = json.decode(k1);
        final a = parsed['k1'];
        return new MaterialApp(
            title: 'Flutter Demo',
            theme: new ThemeData(
                primarySwatch: Colors.blue,
            ),
            home: new MyHomePage(title: 'Flutter Page $a'),
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
    int _counter = 0;
    String ip = "";
    static const platform = const MethodChannel('samples.flutter.io/abc');

    void _incrementCounter() async {
//        setState(() {
//            _counter++;
//        });


        try {
            final int result = await platform.invokeMethod('getBatteryLevel');
        } on PlatformException catch (e) {
            print("exception $e");
        }

    }

    Future getData() async {
        var response = await HttpUtil.getInstance().get("", null);
        setState(() {
            ip = response['origin'];
        });
    }

    @override
    Widget build(BuildContext context) {
        getData();
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
                            'ip: $ip',
                            style: Theme
                                    .of(context)
                                    .textTheme
                                    .display1,
                        ),

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