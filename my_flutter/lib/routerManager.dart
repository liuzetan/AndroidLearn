import 'package:fluro/fluro.dart';
import 'package:flutter/cupertino.dart';
import 'package:my_flutter/test.dart';

class RouteManager {
    static Router router = new Router();
    static Handler testHandler = new Handler(handlerFunc: (BuildContext context, Map<String, dynamic> params) {
        return new Test(params['data'][0]);
    });

    static void defineRoutes() {
        router.define("/test/:data", handler: testHandler);
    }
}
