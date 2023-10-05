import React, {useEffect, useRef, useState} from 'react';
import { Container, Table } from 'reactstrap';
import AppNavbar from '../app/AppNavbar';
import { useParams} from 'react-router-dom';

const TranscriptList = () => {
    const [transcript, setTranscript] = useState([]);
    const [loading, setLoading] = useState(false);
    const { id } = useParams();
    const transcriptList = useRef([]);

    useEffect(() => {
        if (id !== 'new')
            fetch(`/api/user/service/transcript/${id}`)
                .then(res => res.json())
                .then(data => setTranscript(data));
        transcriptList.current = transcript.map(tr => {
            return <tr key={tr.id}>
                <td style={{ whiteSpace: 'nowrap' }}>{tr.language}</td>
                <td style={{ whiteSpace: 'nowrap' }}>{tr.quantity}</td>
                <td style={{ whiteSpace: 'nowrap' }}>{tr.contactPhone}</td>
                <td style={{ whiteSpace: 'nowrap' }}>{tr.isSealed}</td>
            </tr>
        });

    }, [id, setTranscript]);

    if (loading) return <p>Loading...</p>;
    return (
        <div>
            <AppNavbar />
            <Container fluid>
                <h3>Translate scripts</h3>
                <Table className="mt-4">
                    <thead><tr>
                        <th width="30%">Language</th>
                        <th width="30%">Quantity</th>
                        <th width="30%">Contact Phone</th>
                        <th>Sealed</th>
                    </tr></thead>
                    <tbody>{transcriptList.current}</tbody>
                </Table>
            </Container>
        </div>
    );
};

export default TranscriptList;