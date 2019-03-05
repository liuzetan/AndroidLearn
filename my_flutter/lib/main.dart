import 'dart:ui';
import 'package:flutter/material.dart';

void main() => runApp(_widgetForRoute(window.defaultRouteName));

Widget _widgetForRoute(String route) {
  switch (route) {
    case 'route1':
      return Center(
        child: Text('Unknown route1', textDirection: TextDirection.ltr),
      );
    case 'route2':
      return Center(
        child: Text('Unknown rout2', textDirection: TextDirection.ltr),
      );
    default:
      return Center(
        child: Text('Unknown route: $route', textDirection: TextDirection.ltr),
      );
  }
}
