import React, {Component} from 'react';
import { Container, Table } from 'reactstrap';
import AppNavbar from '../app/AppNavbar';
import CourseService from "../services/CourseService";

class CourseList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            courses: []
        }
    }

    componentDidMount() {
        CourseService.getCourse().then(res => {
            this.setState({courses: res.data});
        });
    }

    render() {
        return (
            <div>
                <AppNavbar />
                <Container fluid>
                    <h3 className ="App">Môn học đã đăng ký</h3>
                    <div className="row">
                        <Table className="mt-4 table table-striped table-bordered">
                            <thead  className="text-center align-middle"><tr>
                                <th>Tên môn học</th>
                                <th>Số tín chỉ</th>
                                <th>Ngày bắt đầu</th>
                                <th>Ngày kết thúc</th>
                                <th>Giảng viên</th>
                            </tr></thead>
                            <tbody>
                            { this.state.courses.map( course => (
                                <tr key={course.id}>
                                    <td>{course.course.courseName}</td>
                                    <td>{course.course.creditsNum}</td>
                                    <td>{course.startDate}</td>
                                    <td>{course.endDate}</td>
                                    <td>{course.lecture.lectureName}</td>
                                </tr>
                            ))}
                            </tbody>
                        </Table>
                    </div>
                </Container>
            </div>
        );
    }
}
export default CourseList;