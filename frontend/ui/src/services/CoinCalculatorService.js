import http from "../http-common";

class CoinCalculatorService {
    convert(data) {
        return http.post("/convert", data);
    }
}

export default new CoinCalculatorService();
