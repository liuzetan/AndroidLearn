import 'package:flutter/material.dart';

class CheckBoxListTileDefault extends StatelessWidget {
    final widget;
    final parent;

    const CheckBoxListTileDefault([this.widget, this.parent]) : super();

    @override
    Widget build(BuildContext context) {
        return new CheckboxListTile(
            title: Text("simple example"),
            activeColor: Colors.red,
            value: widget.valBool['val'],
            onChanged: (bool value) {
                parent.setState(() => widget.valBool['val'] = value);
            },
            secondary: const Icon(Icons.print),
        );
    }
}

class CheckBoxListTileStateDefault extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new _CheckBoxListTileStateDefault();
  }

}

class _CheckBoxListTileStateDefault extends State<CheckBoxListTileStateDefault> {
  bool _value = false;
  List<bool> isChecks=[false,false,false];
  void _valueChanged(bool value) {
      for (var i = 0; i < isChecks.length; ++i) {
          isChecks[i] = value;
      }
      if (mounted) {
          setState(() {
                _value = value;
          });
      }
  }

    @override
  Widget build(BuildContext context) {
    return new Column(
        mainAxisAlignment: MainAxisAlignment.start,
        children: <Widget>[
            new Center(
                child: new CheckboxListTile(
                    value: _value,
                    onChanged: _valueChanged,
                    selected: true,
                dense: false,
                isThreeLine: false,
                title: Text("All"),
                controlAffinity: ListTileControlAffinity.trailing,
                subtitle: Text("Select all bellow"),
                activeColor: Colors.red,),
            ),
            new Center(
                child: new CheckboxListTile(
                        value: isChecks[0],
                        title: Text("number 1"),
                        activeColor: _value ? Colors.red : Colors.green,
                        controlAffinity: ListTileControlAffinity.platform,
                        onChanged: (b) {
                            setState(() {
                              isChecks[0] = b;
                            });
                        }),
            ),

            new Center(
                child: new CheckboxListTile(
                        value: isChecks[1],
                        title: Text("number 2"),
                        activeColor: _value ? Colors.red : Colors.green,
                        controlAffinity: ListTileControlAffinity.platform,
                        onChanged: (b) {
                            setState(() {
                                isChecks[1] = b;
                            });
                        }),
            ),
            new Center(
                child: new CheckboxListTile(
                        value: isChecks[2],
                        title: Text("number 3"),
                        activeColor: _value ? Colors.red : Colors.green,
                        controlAffinity: ListTileControlAffinity.platform,
                        onChanged: (b) {
                            setState(() {
                                isChecks[2] = b;
                            });
                        }),
            )
        ],
    );
  }

}