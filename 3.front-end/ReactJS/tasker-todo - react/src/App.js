import './App.css';
import ToDo from './components/ToDo';


function App() {
  return (
    <div className="app">
      <div className="header"> <h3>Tasker</h3> </div>
      <div className="body">
        <ToDo></ToDo>
      </div>
    </div>
  );
}

export default App;
