import React, {useEffect, useState} from 'react';
import PaymentService from "../../services/PaymentService";
import {Container, Table} from "reactstrap";
import moment from "moment/moment";
import {useParams} from "react-router-dom";
import MyAlert from "../../layouts/MyAlert";
function PaymentDetails() {
    const { id } = useParams();
    const [result, setResult] = useState({});

    const [alert, setAlert] = useState(null);
    const showAlert = (message, color) => {
        setAlert({ message, color });
    };
    const verifyPayment = async () => {
        try {
            const res = await PaymentService.verifyPayment(id);
            setResult(res.data);
        } catch (error) {
            showAlert('Lỗi dữ liệu:', 'danger');
        }
    };
    // const getPaymentById = async () => {
    //     try {
    //         const res = await PaymentService.getByPaymentId(id);
    //         setResult(res.data);
    //     } catch (error) {
    //         console.error('Lỗi dữ liệu:', error);
    //     }
    // };

    useEffect(() => {
        const runEffects = async () => {
            await verifyPayment();
            // await getPaymentById();
        };

        runEffects();
    }, []);


    return (
        <div>
            {alert && (
                <MyAlert
                    message={alert.message}
                    color={alert.color}
                />
            )}
            {result ? (
                <Container fluid>
                    <h3 className="App">Thông tin thanh toán</h3>
                    <div className="row">
                        <Table className="mt-5">
                            <tbody>
                            <tr className="border-bottom" style={{ height: '50px' }}>
                                <th>Mã hóa đơn</th>
                                <td>{result.id}</td>
                            </tr>
                            <tr className="border-bottom" style={{ height: '50px' }}>
                                <th>Giá tiền</th>
                                <td>{result.price}</td>
                            </tr>
                            <tr className="border-bottom" style={{ height: '50px' }}>
                                <th>Ngày thanh toán</th>
                                <td>{moment(result.createdDate).format("DD-MM-YYYY HH:mm:ss")}</td>
                            </tr>
                            <tr className="border-bottom" style={{ height: '50px' }}>
                                <th>Tình trạng</th>
                                <td>{result.status}</td>
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

export default PaymentDetails;