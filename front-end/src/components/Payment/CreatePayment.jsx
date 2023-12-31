import React, { useState, useEffect } from 'react';
import {useLocation} from "react-router-dom";
import {Container, Table} from "reactstrap";
import { format } from 'date-fns';
import PaymentService from "../../services/PaymentService";
import MyAlert from "../../layouts/MyAlert";

function CreatePayment() {
    const myParam = useLocation().search;
    const serviceId= new URLSearchParams(myParam).get("service");
    const [payment, setPayment] = useState({});
    const[color, setColor] = useState('');
    const [alert, setAlert] = useState(null);
    const showAlert = (message, color) => {
        setAlert({ message, color });
    };

    useEffect(() => {
        const createPayment = async () => {
            try {
                const res = await PaymentService.createPayment(serviceId)
                setPayment(res.data);
            } catch(error) {
                if(error.response.status === 409)
                    showAlert('Yêu cầu đã có hóa đơn', 'danger');
                else
                    showAlert('Lỗi hệ thống', 'danger');
            }
        }
        createPayment();
    }, []);

    const formatDate = (date) => {
        let d = new Date(date);
        return format(d, "HH:mm:ss - dd/MM/yyyy");
    }

    return (
        <div>
            {payment && (
                <Container fluid>
                    {alert && (
                        <MyAlert
                            message={alert.message}
                            color={alert.color}
                        />
                    )}
                    <h3 className="App">Thanh toán</h3>
                    <div className="row">
                        <Table className="mt-5">
                            <tr className="border-bottom" style={{height:'50px'}}>
                                <th>Thành tiền</th>
                                <td>{payment.price}</td>
                            </tr>
                            <tr className="border-bottom" style={{height:'50px'}}>
                                <th>Ngày lập</th>
                                <td>{payment.createdDate ? formatDate(payment.createdDate) : ''}</td>
                            </tr>
                            <tr className="border-bottom" style={{height:'50px'}}>
                                <th>Trạng thái</th>
                                <td>{payment.status}</td>
                            </tr>
                            <tr className="border-bottom" style={{height:'50px'}}>
                                <th>Mã giao dịch</th>
                                <td>{payment.vnpayTxnred}</td>
                            </tr>
                            <tr className="border-bottom" style={{height:'50px'}}>
                                <th>Link thanh toán</th>
                                <a onMouseEnter={() => setColor('blue')}
                                   onMouseLeave={() => setColor('gray')}
                                   target="_blank" style={{color: color}} type="button" href={payment.url}>
                                    Tại đây</a>
                            </tr>
                        </Table>

                    </div>
                </Container>
            )}
        </div>
    );
}

export default CreatePayment