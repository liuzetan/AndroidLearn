import 'package:dio/dio.dart';

class HttpUtil {
    static HttpUtil instance;
    Dio dio;
    BaseOptions options;

    static HttpUtil getInstance() {
        if (instance == null) {
            instance = new HttpUtil();
        }
        return instance;
    }

    HttpUtil() {
        options = new BaseOptions(
            baseUrl: "https://httpbin.org/ip",
            connectTimeout: 10000,
            receiveTimeout: 3000,
            headers: {},
        );
        dio = new Dio(options);
    }

    get(url, queryParam) async {
        print("get start");
        Response response;
        try {
            response = await dio.get(url, queryParameters: queryParam);
            print("get complete");
        } on DioError catch (e) {
            if (CancelToken.isCancel(e)) {
                print("get canceled");
            }
            print("get failed");
        }
        return response.data;
    }
}