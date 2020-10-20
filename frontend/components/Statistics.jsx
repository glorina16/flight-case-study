import React from 'react';

class Statistics extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            stats : {},
            count200 : ''
        }
    }

      componentDidMount() {
        fetch(`http://localhost:9000/actuator/metrics/http.server.requests`)
          .then(response => response.json())
          .then(data => {
            console.log(data)
            this.setState({stats: data})
          })
          fetch(`http://localhost:9000/actuator/metrics/http.server.requests?tag=status:200`)
            .then(response => response.json())
            .then(data => {
              console.log(data)
              this.setState({count200: data})
            })
      }

    render() {

        return (
            <>
            {
                this.state.stats != undefined && this.state.count200 != ''  ? <div className="hw-block  hw-block--mb hw-block--mt  hw-background-color-primary-lighter">
                            <div>
                                <div className="hw-table">
                                  <table>

                                    <thead>
                                      <tr>
                                        <th>Request 200 Count</th>
                                        <th>Max Response Time </th>
                                        <th>Total Response Time </th>
                                        <th>Request Count</th>
                                      </tr>
                                    </thead>

                                    <tbody>
                                        <tr>
                                        <td>{this.state.count200.measurements?this.state.count200.measurements[0].value:''}</td>
                                        <td>{this.state.stats.measurements ?this.state.stats.measurements[2].value:''}</td>
                                        <td> {this.state.stats.measurements?this.state.stats.measurements[1].value :''}</td>
                                        <td>{this.state.stats.measurements ? this.state.stats.measurements[0].value : ''}</td>
                                        </tr>

                                    </tbody>
                                  </table>
                                </div>
                            </div>
                       </div>:null
                }
            </>
        );
    }
}


export default Statistics;