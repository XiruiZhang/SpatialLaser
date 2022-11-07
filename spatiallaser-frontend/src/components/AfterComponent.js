import React, {useState, useEffect} from 'react';
import axios from 'axios';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from "@mui/material/Paper";
import Button from '@mui/material/Button';

const URL = 'https://cors-everywhere.herokuapp.com/http://spatiallasertestbakend-env.eba-z7sbvmpt.ca-central-1.elasticbeanstalk.com';
//const URL = 'http://localhost:5000';

const AfterComponent = ({tableA}) => {

    const[tableB, setTableB] = useState([]);
    const[show, setShow] = useState(false);

    const fetchData = () => {

        axios.get(URL+'/getNewB').then(
            (res) => {
                setTableB(res.data);
            }
        ).catch(
            (err) => {
                console.log(err);
            }
        )
    }

    function showTable() {
        setShow(true);
        fetchData();
    }

    return(
        <div className="after">
            <div>
                <Button variant="contained" onClick={() => showTable()} style={{marginTop:"20px", marginBottom: "20px"}}>Update</Button>
            </div>
            {show &&
                <div>
                    <div>
                        <h1>Table A</h1>
                        <TableContainer component={Paper}>
                            <Table>
                                <TableHead>
                                    <TableRow>
                                        <TableCell>Street Address</TableCell>
                                        <TableCell>City</TableCell>
                                        <TableCell>State</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {tableA.map(
                                        (row) => (
                                            <TableRow key={row.address}>
                                                <TableCell>{row.address}</TableCell>
                                                <TableCell>{row.city}</TableCell>
                                                <TableCell>{row.state}</TableCell>
                                            </TableRow>
                                        )
                                    )}
                                </TableBody>
                            </Table>
                        </TableContainer>
                    </div>
                    <div>
                        <h1>Table B</h1>
                        <TableContainer component={Paper}>
                            <Table>
                                <TableHead>
                                    <TableRow>
                                        <TableCell>Street Address</TableCell>
                                        <TableCell>City</TableCell>
                                        <TableCell>State</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {tableB.map(
                                        (row) => (
                                            <TableRow key={row.address}>
                                                <TableCell>{row.address}</TableCell>
                                                <TableCell>{row.city}</TableCell>
                                                <TableCell>{row.state}</TableCell>
                                            </TableRow>
                                        )
                                    )}
                                </TableBody>
                            </Table>
                        </TableContainer>
                    </div>
                </div>}
        </div>
    )
}

export default AfterComponent;