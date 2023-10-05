import React, {useEffect, useRef, useState} from 'react';
import { Container, Table } from 'reactstrap';
import AppNavbar from '../app/AppNavbar';
import { useParams} from 'react-router-dom';

const DiplomaList = () => {
    const [diploma, setDiploma] = useState([]);
    const [loading, setLoading] = useState(false);
    const { id } = useParams();

    const diplomaList = useRef([]);
    useEffect(() => {
        if (id !== 'new')
            fetch(`/api/user/service/diploma/${id}`)
                .then(res => res.json())
                .then(data => setDiploma(data));
        diplomaList.current = diploma.map(dip => {
            return <tr key={dip.id}>
                <td style={{ whiteSpace: 'nowrap' }}>{dip.copy}</td>
                <td style={{ whiteSpace: 'nowrap' }}>{dip.phoneContact}</td>
                <td style={{ whiteSpace: 'nowrap' }}>{dip.email}</td>
                <td style={{ whiteSpace: 'nowrap' }}>{dip.diplomaYear}</td>
                <td style={{ whiteSpace: 'nowrap' }}>{dip.diplomaCode}</td>
            </tr>
        });
    }, [id, setDiploma]);

    if (loading) return <p>Loading...</p>;
    return (
        <div>
            <AppNavbar />
            <Container fluid>
                <h3>Bằng tốt nghiệp</h3>
                <Table className="mt-4">
                    <thead><tr>
                        <th>Số lượng bản sao</th>
                        <th>Số điện thoại</th>
                        <th width="30%">Email</th>
                        <th>Năm tốt nghiệp</th>
                        <th width="30%">Mã bằng</th>
                    </tr></thead>
                    <tbody>{diplomaList.current}</tbody>
                </Table>
            </Container>
        </div>
    );
};

export default DiplomaList;