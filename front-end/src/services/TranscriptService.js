import axios from "axios";
import config from "./config";

const TRANSCRIPT_API_BASE_URL = 'http://localhost:8080/api/transcript';

class TranscriptService {
    getTranscript(serviceId) { return axios.get(TRANSCRIPT_API_BASE_URL + '/' + serviceId, config); }
    addTranscript(transcript) { return axios.post(TRANSCRIPT_API_BASE_URL, transcript, config); }
    updateTranscript(transcript, transcriptId) { return axios.put(TRANSCRIPT_API_BASE_URL + '/' + transcriptId, transcript, config); }

}

export default new TranscriptService()