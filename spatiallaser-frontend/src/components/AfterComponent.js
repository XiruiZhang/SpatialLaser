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

const URL = 'http://localhost:8080';

const AfterComponent = () => {

    const[data, setData] = useState([]);
    const[show, setShow] = useState(false);
    //let show = false;

    const fetchData = () => {
        axios.get(URL+'/update').then(
            (res) => {
                console.log(res.data);
                setData(res.data);
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
                {show &&
                    <div>
                        <TableContainer component={Paper}>
                            <Table>
                                <TableHead>
                                    <TableRow>
                                        <TableCell>Street Address</TableCell>
                                        <TableCell align={"right"}>City</TableCell>
                                        <TableCell align={"right"}>State</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {data.map(
                                        (row) => (
                                            <TableRow key={row.address} sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
                                                <TableCell>{row.address}</TableCell>
                                                <TableCell>{row.city}</TableCell>
                                                <TableCell>{row.state}</TableCell>
                                            </TableRow>
                                        )
                                    )}
                                </TableBody>
                            </Table>
                        </TableContainer>
                    </div>}

            </div>
        </div>
    )
}

export default AfterComponent;