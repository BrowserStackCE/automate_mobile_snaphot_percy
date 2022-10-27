mvn test -P automate-percy
export PERCY_TOKEN=YOUR_PERCY_PROJECT_TOKEN
percy upload ./screenshots