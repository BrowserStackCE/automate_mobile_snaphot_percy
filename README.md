# Automate mobile snapshot comparison on Percy

## This repo will help you capture snapshots on browserstack automate and compare them on percy!!!

![BrowserStack Logo](https://d98b8t1nnulk5.cloudfront.net/production/images/layout/logo-header.png?1469004780)

## Using Maven

### Setup

* Clone the repo
* Install dependencies `mvn compile`
* Update `*.conf.json` files inside the `src/test/resources/conf` directory with your [BrowserStack Username and Access Key](https://www.browserstack.com/accounts/settings)
* Add percy Token yo percy-automate.sh file

### Running your tests

- sudo chmod +x automate-percy.sh
- ./percy-automate.sh

### Results

![Screenshot 2022-10-27 at 8 38 24 PM](https://user-images.githubusercontent.com/53310042/198327667-f23fee6f-9949-499c-83be-18fc7a051968.png)


### Notes
* You can export the environment variables for the Username and Access Key of your BrowserStack account

  ```
  export BROWSERSTACK_USERNAME=<browserstack-username> &&
  export BROWSERSTACK_ACCESS_KEY=<browserstack-access-key>
  ```

