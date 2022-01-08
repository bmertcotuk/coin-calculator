import axios from "axios";

const username = 'admin';
const password = 'Qwer1234';
const usernamePasswordBuffer = Buffer.from(username + ':' + password);
const base64data = usernamePasswordBuffer.toString('base64');
const axiosObject = axios.create({
    baseURL: "http://localhost:8090/api/calculator",
    headers: {
        'Content-Type': 'application/json',
        'Authorization': `Basic ${base64data}`,
    }
})

export default axiosObject;
