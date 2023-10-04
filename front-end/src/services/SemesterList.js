import React, { useEffect, useState } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from '../components/AppNavbar';
import { Link } from 'react-router-dom';
import { useCookies } from 'react-cookie';

const SemesterList = () => {
    const [semesters, setSemesters] = useState([]);
    const [loading, setLoading] = useState(false);
    let semesterList;

    useEffect(() => {
        setLoading(true);
        fetch('api/v1/user/semester')
            .then(res => res.json())
            .then(data => {
                setSemesters(data);
                setLoading(false);
            })

        semesterList = semesters.map(semester => {
            return <tr key={semester.id}>
                <td style={{ whiteSpace: 'nowrap' }}>{semester.semesterName}</td>
                <td style={{ whiteSpace: 'nowrap' }}>{semester.note}</td>
                <td style={{ whiteSpace: 'nowrap' }}>{semester.isFinish}</td>
            </tr>
        });

    }, []);

    if (loading) return <p>Loading...</p>;
    return (
        <div>
            <AppNavbar />
            <Container fluid>
                <h3>Khóa học sinh viên</h3>
                <Table className="mt-3">
                    <thead><tr>
                        <th width="40%">Học kỳ</th>
                        <th width="30%">Ghi chú</th>
                        <th width="30%">Trạng thái</th>
                    </tr></thead>
                    <tbody>{semesterList}</tbody>
                </Table>
            </Container>
        </div>
    );
};

export default SemesterList;