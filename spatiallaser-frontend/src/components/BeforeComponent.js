import React, {useState, useEffect} from 'react';
import axios from 'axios';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

const URL = 'https://cors-everywhere.herokuapp.com/http://spatiallasertestbakend-env.eba-z7sbvmpt.ca-central-1.elasticbeanstalk.com';

const BeforeComponent = ({tableA}) => {

    const[tableB, setTableB] = useState([]);

    useEffect(() => {
        fetchData();
    }, []);
    
    const fetchData = () => {

        axios.get(URL+'/getB').then(
            (res) => {
                setTableB(res.data);
            }
        ).catch(
            (err) =>{
                console.log(err);
            }
        )
    }

    return(
            <div className="before" >
                <div>
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

            </div>

    )
}

export default BeforeComponent;