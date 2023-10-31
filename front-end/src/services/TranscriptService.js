import axios from "axios";
import cookie from "react-cookies";
import {createHeaders} from "./header";

const TRANSCRIPT_API_BASE_URL = 'http://localhost:8080/api/transcript';

class TranscriptService {
    getTranscript(serviceId) { return axios.get(TRANSCRIPT_API_BASE_URL + '/' + serviceId, createHeaders()); }
    addTranscript(transcript) { return axios.post(TRANSCRIPT_API_BASE_URL, transcript, createHeaders()); }
    updateTranscript(transcript, transcriptId) { return axios.put(TRANSCRIPT_API_BASE_URL + '/' +
        transcriptId, transcript, createHeaders()); }

}

export default new TranscriptService()