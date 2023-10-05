import React, {useEffect, useRef, useState} from 'react';
import { Container, Table } from 'reactstrap';
import AppNavbar from '../app/AppNavbar';

const CourseList = () => {
    const [courses, setCourses] = useState([]);
    const [loading, setLoading] = useState(false);

    const courseList =useRef([]);
    useEffect(() => {
        setLoading(true);
        fetch('api/v1/course')
            .then(res => res.json())
            .then(data => {
                setCourses(data);
                setLoading(false);
            })
        courseList.current = courses.map(course => {
            return <tr key={course.id}>
                <td style={{ whiteSpace: 'nowrap' }}>{course.courseName}</td>
                <td style={{ whiteSpace: 'nowrap' }}>{course.creditsNum}</td>
                <td style={{ whiteSpace: 'nowrap' }}>{course.note}</td>
            </tr>
        });
    }, []);

    if (loading) return <p>Loading...</p>;
    return (
        <div>
            <AppNavbar />
            <Container fluid>
                <h3>Môn học đã đăng ký</h3>
                <Table className="mt-3">
                    <thead><tr>
                        <th width="30%">Tên môn học</th>
                        <th width="30%">Số tín chỉ</th>
                        <th width="40%">Ghi chú</th>
                    </tr></thead>
                    <tbody>{courseList.current}</tbody>
                </Table>
            </Container>
        </div>
    );
};

export default CourseList;