import React, { useEffect, useState } from 'react';
import {Alert, Button, Container, Row, Table} from 'reactstrap';
import ServiceCate from "../../../services/ServiceCate";
import { useNavigate, useParams } from 'react-router-dom';
import UserService from "../../../services/UserService";

function CateList() {
    const [user, setUser] = useState({});
    const [cates, setCates] = useState([]);
    const nav = useNavigate();
    const { id } = useParams();
    const [error, setError] = useState('');

    useEffect(() => {
        const getUser = async () => {
            try {
                const res = await UserService.getUser();
                setUser(res.data);
            }
            catch (error) { console.error('Lỗi lấy data: ', error); }
        }
        getUser().then();

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
                console.error('Lỗi khi thay đổi trạng thái:', error);
            });
    }



    const updateCate = (cate) => {
        nav(`/moderator/service-cate/update/${cate.id}`, {
            state: {
                serviceCateName: cate.serviceCateName,
                price: cate.price,
                isAvailable: cate.isAvailable,
                description: cate.description,
                numOfDate: cate.numOfDate,
            }
        });
    }

    return (
        <Container fluid className='mb-5'>
            <h3 className="App">Đăng ký dịch vụ</h3>
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
            { error ? <Alert color="danger" className="fixed-bottom"
                             style={{marginBottom:'5rem', marginLeft:'25%', marginRight:'25%'}}
                             onMouseEnter={() => setError('')}>{error}
            </Alert> : <></> }
        </Container>
    );
}

export default CateList;