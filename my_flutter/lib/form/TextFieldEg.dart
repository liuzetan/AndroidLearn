import 'package:flutter/material.dart';

class DefaultTextField extends StatelessWidget {
    @override
    Widget build(BuildContext context) {
        return new Container(
                padding: EdgeInsets.all(20),
                child: new Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: <Widget>[
                        Text('Please Input: ', style: TextStyle(fontSize: 15, height: 1.2, color: Colors
                                .blue), textAlign: TextAlign.left,),
                        new TextField(),
                    ],
                ));
    }

}

class CustomTextField extends StatelessWidget {
    void _textChanged(String str) {
        print(str);
    }

    @override
    Widget build(BuildContext context) {
        return new Container(
                padding: EdgeInsets.all(20),
                child: new TextField(
                    keyboardType: TextInputType.number,
                    decoration: InputDecoration(
                            contentPadding: EdgeInsets.all(10.0),
                            icon: Icon(Icons.text_fields),
                            labelText: 'Please input your name: ',
                            helperText: 'your real name'
                    ),
                    onChanged: _textChanged,
                    autofocus: false,
                ));
    }

}