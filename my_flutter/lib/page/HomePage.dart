import 'package:flutter/material.dart';
import 'package:my_flutter/form/CheckBoxEf.dart';
import 'package:my_flutter/form/TextFieldEg.dart';

class HomePage extends StatefulWidget {
    final Map<String, bool> valBool = {'val': true};
    @override
    State<StatefulWidget> createState() {
        return new HomePageState();
    }

}

class HomePageState extends State<HomePage> {

    @override
    Widget build(BuildContext context) {
        return new Container(
                alignment: Alignment.center,
                child: new Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    verticalDirection: VerticalDirection.down,
                    children: <Widget>[
                        new Row(
                            children: <Widget>[
                                new Expanded(child: new DefaultTextField()),
                                new Flexible(child: new CustomTextField())
                            ],
                        ),
                        new CheckBoxListTileDefault(context.widget, this),
                        new CheckBoxListTileStateDefault(),
                    ],
                ));
    }

}