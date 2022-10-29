import React, {useState, useEffect} from 'react';
import axios from 'axios';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

const URL = 'http://localhost:8080';

const BeforeComponent = () => {

    const[tableA, setTableA] = useState([]);
    const[tableB, setTableB] = useState([]);

    useEffect(() => {
        fetchData();
    }, []);
    
    const fetchData = () => {
        axios.get(URL+'/getA').then(
            (res) =>{
                console.log(res.data);
                setTableA(res.data);
            }
        ).catch(
            (err) =>{
                console.log(err);
            }
        )
        axios.get(URL+'/getB').then(
            (res) => {
                console.log(res.data);
                setTableB(res.data);
            }
        ).catch(
            (err) =>{
                console.log(err);
            }
        )
    }

    return(
        <div>
            <div className="before">
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
                            {tableA.map(
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

                    <Table>
                        <TableHead>
                            <TableRow>
                                <TableCell>Street Address</TableCell>
                                <TableCell align={"right"}>City</TableCell>
                                <TableCell align={"right"}>State</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {tableB.map(
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
            </div>
            <div className="after"></div>

        </div>
    )
}

export default BeforeComponent;