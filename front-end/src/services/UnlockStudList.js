import React, {useEffect, useRef, useState} from 'react';
import { Container, Table } from 'reactstrap';
import AppNavbar from '../app/AppNavbar';
import { useParams} from 'react-router-dom';

const UnlockStudentList = () => {
    const [unlockStudent, setUnlockStudent] = useState([]);
    const [loading, setLoading] = useState(false);
    const { id } = useParams();
    const unlockStudentList = useRef([]);

    useEffect(() => {
        if (id !== 'new')
            fetch(`/api/user/service/unlock-student/${id}`)
                .then(res => res.json())
                .then(data => setUnlockStudent(data));
        unlockStudentList.current = unlockStudent.map(uns => {
            return <tr key={uns.id}>
                <td style={{ whiteSpace: 'nowrap' }}>{uns.content}</td>
                <td style={{ whiteSpace: 'nowrap' }}>{uns.image}</td>
            </tr>
        });

    }, [id, setUnlockStudent]);

    if (loading) return <p>Loading...</p>;
    return (
        <div>
            <AppNavbar />
            <Container fluid>
                <h3>Mở khóa sinh viên</h3>
                <Table className="mt-2">
                    <thead><tr>
                        <th width="70%">Nội dung</th>
                        <th width="30%">Hình ảnh</th>
                    </tr></thead>
                    <tbody>{unlockStudentList}</tbody>
                </Table>
            </Container>
        </div>
    );
};

export default UnlockStudentList;