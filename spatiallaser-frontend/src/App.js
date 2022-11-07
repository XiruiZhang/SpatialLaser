//import logo from './logo.svg';
import './App.css';
import BeforeComponent from "./components/BeforeComponent";
import AfterComponent from "./components/AfterComponent";
import {useEffect, useState} from "react";
import axios from "axios";


const URL = 'https://cors-everywhere.herokuapp.com/http://spatiallasertestbakend-env.eba-z7sbvmpt.ca-central-1.elasticbeanstalk.com';

function App() {

    const[tableA, setTableA] = useState([]);

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
    }

  return (
    <div className="App">
        <div className="BeforeContainer">
            <BeforeComponent tableA={tableA}/>
        </div>
        <div className="AfterContainer">
            <AfterComponent tableA={tableA}/>
        </div>
    </div>
  );
}

export default App;
