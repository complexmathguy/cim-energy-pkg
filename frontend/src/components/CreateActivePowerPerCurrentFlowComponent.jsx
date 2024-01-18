import React, { Component } from 'react'
import ActivePowerPerCurrentFlowService from '../services/ActivePowerPerCurrentFlowService';

class CreateActivePowerPerCurrentFlowComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            // step 2
            id: this.props.match.params.id,
        }
    }

    // step 3
    componentDidMount(){

        // step 4
        if(this.state.id === '_add'){
            return
        }else{
            ActivePowerPerCurrentFlowService.getActivePowerPerCurrentFlowById(this.state.id).then( (res) =>{
                let activePowerPerCurrentFlow = res.data;
                this.setState({
                });
            });
        }        
    }
    saveOrUpdateActivePowerPerCurrentFlow = (e) => {
        e.preventDefault();
        let activePowerPerCurrentFlow = {
                activePowerPerCurrentFlowId: this.state.id,
            };
        console.log('activePowerPerCurrentFlow => ' + JSON.stringify(activePowerPerCurrentFlow));

        // step 5
        if(this.state.id === '_add'){
            activePowerPerCurrentFlow.activePowerPerCurrentFlowId=''
            ActivePowerPerCurrentFlowService.createActivePowerPerCurrentFlow(activePowerPerCurrentFlow).then(res =>{
                this.props.history.push('/activePowerPerCurrentFlows');
            });
        }else{
            ActivePowerPerCurrentFlowService.updateActivePowerPerCurrentFlow(activePowerPerCurrentFlow).then( res => {
                this.props.history.push('/activePowerPerCurrentFlows');
            });
        }
    }
    

    cancel(){
        this.props.history.push('/activePowerPerCurrentFlows');
    }

    getTitle(){
        if(this.state.id === '_add'){
            return <h3 className="text-center">Add ActivePowerPerCurrentFlow</h3>
        }else{
            return <h3 className="text-center">Update ActivePowerPerCurrentFlow</h3>
        }
    }
    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                {
                                    this.getTitle()
                                }
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                        </div>

                                        <button className="btn btn-success" onClick={this.saveOrUpdateActivePowerPerCurrentFlow}>Save</button>
                                        <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                   </div>
            </div>
        )
    }
}

export default CreateActivePowerPerCurrentFlowComponent
