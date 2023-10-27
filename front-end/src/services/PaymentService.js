import axios from "axios";
import config from "./config";

const PAYMENT_API_URL = 'http://localhost:8080/api/payment';

class PaymentService{
    createPayment(id) {
        return axios.get(`${PAYMENT_API_URL}/create?service=${id}`, config);
    }

    getResult(vnp_Amount,vnp_OrderInfo, vnp_TxnRef, vnp_PayDate, vnp_TransactionStatus) {
        return axios.get(`${PAYMENT_API_URL}/result?vnp_Amount=${vnp_Amount}&vnp_OrderInfo=${vnp_OrderInfo}
        &vnp_TxnRef=${vnp_TxnRef}&vnp_PayDate=${vnp_PayDate}&vnp_TransactionStatus=${vnp_TransactionStatus}`, config);
    }

    getByPaymentId(id){
        return axios.get(PAYMENT_API_URL+"/"+id, config);
    }

    verifyPayment(id){
        return axios.get(PAYMENT_API_URL+"/" + id + "/verify", config);
    }
}
export default new PaymentService();