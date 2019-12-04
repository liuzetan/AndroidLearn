import 'dart:developer';

import 'package:flutter/material.dart';
import 'dart:convert';
import 'package:flutter/services.dart';
import 'package:my_flutter/HttpUtil.dart';
import 'package:my_flutter/page/AddPage.dart';
import 'package:my_flutter/page/HomePage.dart';
import 'package:my_flutter/page/ListPage.dart';
import 'package:my_flutter/page/MessagePage.dart';
import 'package:my_flutter/routerManager.dart';


class Test extends StatelessWidget {
    // This widget is the root of your application.
    String k;

    Test(this.k);

    @override
    Widget build(BuildContext context) {
        final parsed = json.decode(k);
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

    var _curIndex = 0;
    var _texts = ["Home", "List", "Message", "Add"];
    var _icons = [Icons.home, Icons.list, Icons.message, Icons.add];
    var _bodys = [new HomePage(), new ListPage(), new MessagePage(), new AddPage()];

    void _incrementCounter() async {
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

    void _onItemTapped(int index) {
        if (mounted) {
            setState(() {
                _curIndex = index;
            });
        }
    }

    @override
    Widget build(BuildContext context) {
//        getData();
        return new Scaffold(
            appBar: new AppBar(
                title: new Text(widget.title),
            ),
            body: _bodys[_curIndex],
            bottomNavigationBar: new BottomNavigationBar(
                    type: BottomNavigationBarType.fixed,
                    iconSize: 24.0,
                    currentIndex: _curIndex,
                    onTap: _onItemTapped,
                    fixedColor: Colors.red,
                    items: <BottomNavigationBarItem>[
                        BottomNavigationBarItem(title: Text(_texts[0]), icon: Icon(_icons[0])),
                        BottomNavigationBarItem(title: Text(_texts[1]), icon: Icon(_icons[1])),
                        BottomNavigationBarItem(title: Text(_texts[2]), icon: Icon(_icons[2])),
                        BottomNavigationBarItem(title: Text(_texts[3]), icon: Icon(_icons[3])),
                    ]),// This trailing comma makes auto-formatting nicer for build methods.
        );
    }
}