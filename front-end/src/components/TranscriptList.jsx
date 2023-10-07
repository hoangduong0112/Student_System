import React, {Component} from 'react';
import { Container, Table } from 'reactstrap';
import Header from '../app/Header';
import TranscriptService from "../services/TranscriptService";

class TranscriptList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            transcripts: []
        }
    }

    componentDidMount() {
        TranscriptService.getTranscript().then(res => {
            this.setState({transcripts: res.data});
        });
    }

    render() {
        return (
            <div>
                <Header />
                <Container fluid>
                    <h3 className ="App">Bảng điểm sinh viên</h3>
                    <div className="row">
                        <Table className="mt-6 table table-striped table-bordered">
                            <thead  className="text-center align-middle"><tr>
                                <th width="20%">Ngôn ngữ</th>
                                <th>Số bản sao</th>
                                <th>Học kỳ bắt đầu</th>
                                <th>Học kỳ kết thúc</th>
                                <th>Số điện thoại</th>
                                <th>Trạng thái</th>
                            </tr></thead>
                            <tbody>
                            { this.state.transcripts.map( transcript => (
                                <tr key={transcript.id}>
                                    <td>{transcript.language}</td>
                                    <td>{transcript.fromSemester.semesterName}</td>
                                    <td>{transcript.toSemester.semesterName}</td>
                                    <td>{transcript.quantity}</td>
                                    <td>{transcript.contactPhone}</td>
                                    <td>{transcript.isSealed}</td>
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
export default TranscriptList;