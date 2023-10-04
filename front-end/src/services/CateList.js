import React, { useEffect, useState } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from '../components/AppNavbar';
import {Link, useParams} from 'react-router-dom';

const CateList = () => {
    const [cates, setCates] = useState([]);
    const [loading, setLoading] = useState(false);
    const { id } = useParams();

    function setCateList() {
        return cates.map(cate => {
            return <tr key={cate.id}>
                <td style={{ whiteSpace: 'nowrap' }}>{cate.serviceCateName}</td>
                <td style={{ whiteSpace: 'nowrap' }}>{cate.price}</td>
                <td style={{ whiteSpace: 'nowrap' }}>{cate.isAvailable}</td>
                <td style={{ whiteSpace: 'nowrap' }}>{cate.description}</td>
            </tr>
        });
    }
    let cateList;
    useEffect(() => {
        setLoading(true);
        fetch('api/v1/service-cate')
            .then(res => res.json())
            .then(data => {
                setCates(data);
                setLoading(false);
            })
        setCateList(cateList);
    }, []);

    useEffect(() => {
        if (id !== 'new')
            fetch(`/api/v1/service-cate/${id}`)
                .then(res => res.json())
                .then(data => setCates(data));
        setCateList(cateList);
    }, [id, setCates]);

    if (loading) return <p>Loading...</p>;
    return (
        <div>
            <AppNavbar />
            <Container fluid>
                <h3>Thông tin sinh viên</h3>
                <Table className="mt-4">
                    <thead><tr>
                        <th width="30%">Dịch vụ</th>
                        <th width="20%">Đơn giá</th>
                        <th>Trạng thái</th>
                        <th width="30%">Nội dung</th>
                    </tr></thead>
                    <tbody>{cateList}</tbody>
                </Table>
            </Container>
        </div>
    );
};

export default CateList;