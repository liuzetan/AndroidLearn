import 'package:flutter/material.dart';

class ListPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        ListTile(title: Text("ListTile")),
        Expanded(
          child: ListView.separated(
            itemBuilder: (BuildContext context, int index) {
              return Row(
                children: <Widget>[
                  Image.asset(
                    "images/humbger.jpeg",
                    width: 30,
                    height: 30,
                  ),
                  Container(
                    alignment: Alignment(-1.0, 0),
                    padding: EdgeInsets.fromLTRB(10, 0, 0, 0),
                    width: 100,
                    height: 50,
                    child: Text(
                      "text $index",
                    ),
                  ),
                ],
              );
            },
            separatorBuilder: (BuildContext context, int index) {
              return Container(height: 1.0, color: Colors.blue);
            },
            itemCount: 70,
            shrinkWrap: true,
            padding: EdgeInsets.all(18),
          ),
        ),
      ],
    );
  }
}
