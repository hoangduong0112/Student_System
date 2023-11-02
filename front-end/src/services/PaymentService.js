import axios from "axios";
import cookie from "react-cookies";
import {createHeaders} from "./header";

const PAYMENT_API_URL = 'http://localhost:8080/api/payment';

class PaymentService{
    createPayment(id) {
        return axios.get(`${PAYMENT_API_URL}/create?service=${id}`, createHeaders())
    }

    getResult(vnp_Amount,vnp_OrderInfo, vnp_TxnRef, vnp_PayDate, vnp_TransactionStatus) {
        return axios.get(`${PAYMENT_API_URL}/result?vnp_Amount=${vnp_Amount}&vnp_OrderInfo=${vnp_OrderInfo}
        &vnp_TxnRef=${vnp_TxnRef}&vnp_PayDate=${vnp_PayDate}&vnp_TransactionStatus=${vnp_TransactionStatus}`, createHeaders())
    }

    getByPaymentId(id){
        return axios.get(PAYMENT_API_URL+"/"+id, createHeaders())
    }

    getByServiceId(serviceId){
        return axios.get(PAYMENT_API_URL+"?service_id="+serviceId, createHeaders());
    }

    verifyPayment(id){
        return axios.get(PAYMENT_API_URL+"/" + id + "/verify", createHeaders());
    }
}
export default new PaymentService();