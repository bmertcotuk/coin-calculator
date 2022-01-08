import http from "../http-common";

class CoinCalculatorService {
    convert(data) {
        return http.post("/convert", data);
    }
    store(data) {
        return http.post("/store", data);
    }
}

export default new CoinCalculatorService();
