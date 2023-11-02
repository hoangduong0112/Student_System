import React, { useState, useEffect } from 'react';
import PaymentService from "../../services/PaymentService";
import {Container, Table} from "reactstrap";
import { format } from 'date-fns';
import {useLocation} from "react-router-dom";
import moment from 'moment';
import MyAlert from "../../layouts/MyAlert";
function PaymentStatus() {
    const myParam = useLocation().search;
    const vnp_Amount= new URLSearchParams(myParam).get("vnp_Amount");
    const vnp_OrderInfo= new URLSearchParams(myParam).get("vnp_OrderInfo");
    const vnp_TxnRef= new URLSearchParams(myParam).get("vnp_TxnRef");
    const vnp_PayDate= new URLSearchParams(myParam).get("vnp_PayDate");
    const vnp_TransactionStatus= new URLSearchParams(myParam).get("vnp_TransactionStatus");
    const [status, setStatus] = useState({});

    const [alert, setAlert] = useState(null);
    const showAlert = (message, color) => {
        setAlert({ message, color });
    };
    const getStatusAfterPay = async () =>{
        try {
            const res = await PaymentService.getResult(vnp_Amount, vnp_OrderInfo, vnp_TxnRef, vnp_PayDate, vnp_TransactionStatus);
            setStatus(res.data);
        } catch (error) {
            showAlert('Gặp sự cố không rõ', 'danger')
        }
    }

    useEffect(() => {
        getStatusAfterPay();
    }, []);

    return (
        <div>
            {alert && (
                <MyAlert
                    message={alert.message}
                    color={alert.color}
                />
            )}
            {status ? (
                <Container fluid>
                    <h3 className="App">Thông tin thanh toán</h3>
                    <div className="row">
                        <Table className="mt-5">
                            <tbody>
                            <tr className="border-bottom" style={{ height: '50px' }}>
                                <th>Tên</th>
                                <td>{status.title}</td>
                            </tr>
                            <tr className="border-bottom" style={{ height: '50px' }}>
                                <th>Trạng thái</th>
                                <td>{status.status}</td>
                            </tr>
                            <tr className="border-bottom" style={{ height: '50px' }}>
                                <th>Ngày lập</th>
                                <td>{moment(status.date).format("DD-MM-YYYY HH:mm:ss")}</td>
                            </tr>
                            <tr className="border-bottom" style={{ height: '50px' }}>
                                <th>Số tiền</th>
                                <td>{status.amount}</td>
                            </tr>
                            <tr className="border-bottom" style={{ height: '50px' }}>
                                <th>Nội dung</th>
                                <td>{status.message}</td>
                            </tr>
                            </tbody>
                        </Table>
                    </div>
                </Container>
            ) : (
                <h3>Loading...</h3>
            )}
        </div>
    );
}

export default PaymentStatus
