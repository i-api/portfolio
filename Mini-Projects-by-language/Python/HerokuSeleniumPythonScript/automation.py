#https://www.youtube.com/watch?v=YbGAUEjTKg4 video on script
#https://www.youtube.com/watch?v=rfdNIOYGYVI video on deployment to heroku

# This script will automatically submit this google docs form, so I can get free shit (JHU merchandise)) 
from selenium.webdriver.common.by import By
from selenium import webdriver #pip install selenium
from webdriver_manager.firefox import GeckoDriverManager #pip install webdriver-manager
import time


def main():
    print("\n"),
    runNumber = 0
    while(True):
        """
        current_hour = int(time.localtime().tm_hour)
        if (current_hour == 24 or current_hour >= 1 and current_hour <= 6):
            print("current hour: " + str(current_hour))
            time.sleep(3600)
        else:
        """
        current_hour = int(time.localtime().tm_hour); runNumber += 1
        print("-----------------------------------------------    ---------------------")
        print("current hour: " + str(current_hour) + "    runNumber: " + str(runNumber))
        run_script()
        time.sleep(1)
        #time.sleep(10800) #3 hours


def run_script():
    #WebDriverFirefox
    #webDriver = webdriver.Firefox(executable_path=GeckoDriverManager().install())

    #webDriverChrome
    webDriver = webdriver.Chrome() #pip install chrome-driver
    webDriver.get("https://docs.google.com/forms/d/e/1FAIpQLSehbbr5-GyNsFi7wWia5kSB3KJA28CoiY7-JznuwBrp54P3QA/viewform")

    time.sleep(5)

    FullName = "Tim Chau"
    last = webDriver.find_element(By.XPATH, '/html/body/div/div[2]/form/div[2]/div/div[2]/div[1]/div/div/div[2]/div/div[1]/div/div[1]/input')
    last.send_keys(FullName)


    JHUemail = "jchau3@jhu.edu"
    last = webDriver.find_element(By.XPATH, '/html/body/div/div[2]/form/div[2]/div/div[2]/div[2]/div/div/div[2]/div/div[1]/div/div[1]/input')
    last.send_keys(JHUemail)

    SubmitButton = webDriver.find_element(By.XPATH, '/html/body/div/div[2]/form/div[2]/div/div[3]/div[1]/div[1]/div/span/span')
    SubmitButton.click() #//*[@id="mG61Hd"]/div[2]/div/div[3]/div[1]/div[1]/div/div[2]

    get_confirmation_div_text = webDriver.find_element(By.CSS_SELECTOR, '.freebirdFormviewerViewResponseConfirmationMessage')
    print(get_confirmation_div_text.text)
    if ((get_confirmation_div_text.text) == "Thank you for not taking a reusable bag! Follow Homewood Recycling on Instagram for more information at https://www.instagram.com/homewoodrecycling/"):
        print("Test was successful. (String matched: Thank you for not taking a reusable bag!...)")
    else:
        print("Test failed. (String didn't match: Thank you for not taking a reusable bag!...) ")

    time.sleep(2)
    webDriver.quit()





if __name__ == '__main__':
    main()


""" #Absolute paths
import os
THIS_FOLDER = os.path.dirname(os.path.abspath(__file__))
my_file = os.path.join(THIS_FOLDER, 'myfile.txt')

"""