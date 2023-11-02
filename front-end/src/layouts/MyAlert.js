import React from "react";

import { UncontrolledAlert} from 'reactstrap';

const MyAlert = ({ message, color }) => {
    return (
        <UncontrolledAlert color={color} style={{marginTop:"5%", marginRight:"10%", marginLeft:"10%"}}
               onMouseEnter={() => ('')}>
            {message}
        </UncontrolledAlert >
    );
};

export default MyAlert;