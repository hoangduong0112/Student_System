import React, {useEffect, useRef, useState} from 'react';
import { Container, Table } from 'reactstrap';
import AppNavbar from '../app/AppNavbar';
import { useParams} from 'react-router-dom';

const CateList = () => {
    const [cates, setCates] = useState([]);
    const [loading, setLoading] = useState(false);
    const { id } = useParams();
    const cateList = useRef([cates]);

    useEffect(() => {
        setLoading(true);
        fetch('api/guest/service-cate')
            .then(res => res.json())
            .then(data => {
                setCates(data);
                setLoading(false);
            })
    }, []);

    useEffect(() => {
        if (id !== 'new')
            fetch(`/api/guest/service-cate/${id}`)
                .then(res => res.json())
                .then(data => setCates(data));
    }, [id, setCates]);

    cateList.current.map((cate) => {
            return (
                <tr key={cate.id}>
                    <td style={{ whiteSpace: 'nowrap' }}>{cate.serviceCateName}</td>
                    <td style={{ whiteSpace: 'nowrap' }}>{cate.price}</td>
                    <td style={{ whiteSpace: 'nowrap' }}>{cate.isAvailable}</td>
                    <td style={{ whiteSpace: 'nowrap' }}>{cate.description}</td>
                </tr>
            );
        })

    if (loading) return <p>Loading...</p>;
    return (
        <div>
            <AppNavbar />
            <Container fluid>
                <h3>Đăng ký dịch vụ</h3>
                <Table className="mt-4">
                    <thead><tr>
                        <th width="30%">Dịch vụ</th>
                        <th width="20%">Đơn giá</th>
                        <th>Trạng thái</th>
                        <th width="30%">Nội dung</th>
                    </tr></thead>
                    <tbody>{cateList.current}</tbody>
                </Table>
            </Container>
        </div>
    );
};

export default CateList;