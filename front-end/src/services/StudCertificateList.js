import React, {useEffect, useRef, useState} from 'react';
import { Container, Table } from 'reactstrap';
import AppNavbar from '../app/AppNavbar';
import { useParams} from 'react-router-dom';

const StudCertificateList = () => {
    const [studCertificate, setStudCertificate] = useState([]);
    const [loading, setLoading] = useState(false);
    const { id } = useParams();
    const studCertificateList = useRef([]);

    useEffect(() => {
        if (id !== 'new')
            fetch(`/api/user/service/stud-certification/${id}`)
                .then(res => res.json())
                .then(data => setStudCertificate(data));
        studCertificateList.current = studCertificate.map(stc => {
            return <tr key={stc.id}>
                <td style={{ whiteSpace: 'nowrap' }}>{stc.email}</td>
                <td style={{ whiteSpace: 'nowrap' }}>{stc.vietCopy}</td>
                <td style={{ whiteSpace: 'nowrap' }}>{stc.engCopy}</td>
                <td style={{ whiteSpace: 'nowrap' }}>{stc.phoneContact}</td>
                <td style={{ whiteSpace: 'nowrap' }}>{stc.content}</td>
            </tr>
        });

    }, [id, setStudCertificate]);

    if (loading) return <p>Loading...</p>;
    return (
        <div>
            <AppNavbar />
            <Container fluid>
                <h3>Chứng nhận sinh viên</h3>
                <Table className="mt-5">
                    <thead><tr>
                        <th width="20%">Email</th>
                        <th >Bản sao tiếng Việt</th>
                        <th>Bản sao tiếng Anh</th>
                        <th>Số điện thoại</th>
                        <th width="30%">Nội dung</th>
                    </tr></thead>
                    <tbody>{studCertificateList.current}</tbody>
                </Table>
            </Container>
        </div>
    );
};

export default StudCertificateList;