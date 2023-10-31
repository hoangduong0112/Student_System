import React, {useContext, useEffect, useState} from 'react';
import {Alert, Button, Container, Row, Table} from 'reactstrap';
import ServiceCate from "../../../services/ServiceCate";
import { useNavigate, useParams } from 'react-router-dom';
import UserService from "../../../services/UserService";
import {UserContext} from "../../../app/App";
import MyAlert from "../../../layouts/MyAlert";

function CateList() {
    const [cates, setCates] = useState([]);
    const nav = useNavigate();
    const { id } = useParams();
    const [user, setUser] = useContext(UserContext);

    const [alert, setAlert] = useState(null);
    const showAlert = (message, color) => {
        setAlert({ message, color });
    };

    useEffect(() => {
        ServiceCate.getAllServiceCate().then(res => {
                setCates(res.data);
            });
    }, [id]);

    const changeStatus = (cate) => {
        ServiceCate.changeCate(cate.id)
            .then(() => {
                setCates((prevCates) => {
                    return prevCates.map((c) => {
                        if (c.id === cate.id) {
                            return { ...c, isAvailable: !c.isAvailable };
                        }
                        return c;
                    });
                });
            })
            .catch((error) => {
                showAlert('Lỗi khi thay đổi trạng thái:', 'danger');
            });
    }



    const updateCate = (cate) => {
        nav(`/moderator/service-cate/update/${cate.id}`);
    }

    return (
        <Container fluid className='mb-5'>
            <h3 className="App">Đăng ký dịch vụ</h3>
            {alert && (
                <MyAlert
                    message={alert.message}
                    color={alert.color}
                />
            )}
            <Row>
                <Table className="mt-3 table-striped table-bordered">
                    <thead className="App">
                    <tr>
                        <th>Dịch vụ</th>
                        <th>Đơn giá</th>
                        <th>Trạng thái</th>
                        <th>Nội dung</th>
                        <th>Thời gian cấp</th>
                        <th>Thao tác</th>
                    </tr>
                    </thead>
                    <tbody>
                    {cates.map((cate) => (
                        <tr key={cate.id}>
                            <td>{cate.serviceCateName}</td>
                            <td>{cate.price}</td>
                            <td>{cate.isAvailable ? 'Còn mở' : 'Đã đóng'}</td>
                            <td>{cate.description}</td>
                            <td>{cate.numOfDate} ngày</td>
                            <td className="App">
                                {user.role === 'MODERATOR' ? <>
                                    <Button color="success" className="m-1"
                                            onClick={() => {updateCate(cate)}}>Chỉnh sửa
                                    </Button>
                                    <Button color="warning" className="m-1"
                                            onClick={() => {changeStatus(cate)}}>
                                        { cate.isAvailable ? 'Đóng dịch vụ' : 'Mở dịch vụ' }
                                    </Button>
                                </> : <></>}
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </Table>
            </Row>
            <Row className="float-end" style={{paddingBottom: '15%'}}>
                <Button color="info" onClick={() => nav('/moderator/get-request')}>Lịch sử đăng ký</Button>
            </Row>
        </Container>
    );
}

export default CateList;