pipeline{

    agent any

    stages{

        stage("Build"){
            steps{
                echo("Builiding the project")
            }
        }

        stage("Unit test"){
            steps{
                echo("Run UTs")
            }
        }

        stage("Deploy to Dev "){
            steps{
                echo("Deploy to Dev")
            }
        }

        stage("Deploy to QA "){
            steps{
                echo("Deploy to QA")
            }
        }

        stage("Run the smoke test"){
            steps{
                echo("Run the smoke test")
            }
        }

         stage("Run the sanity tests "){
            steps{
                echo("Run the sanity tests")
            }
        }

          stage("Run the regression tests "){
            steps{
                echo("Run the regression tests")
            }
        }

      stage("Deploy to Prod "){
            steps{
                echo("Deploy to Prod")
            }
        }
    }

}