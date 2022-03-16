


def main():
    print("Type c + enter for custom input, otherwise hit enter for defaults")

    if input() == "c":
        runCustom()
    else:
        runDefault()


def runDefault():
    start = 22; virgintest = True
    retire = 59
    workingLife = retire - start
    salary = 51916.27
    postTaxSavings = (salary * 0.2)
    networth = 0


    for i in range(workingLife + 1):
        networth = (networth * 1.07) + postTaxSavings
        round_networth = (int)(networth//1) #round(networth, 2) #2 decimal places
        networth_string = ('{0:,}'.format(round_networth))

        print(str(start) + "  net_worth: " + networth_string, end ="")
        if ((virgintest == True) and networth >= 1000000): 
            virgintest = False
            print("| You hit 1 million!") #\033[A  # \033[F
        else:
            print('')
        start += 1


def runCustom():
    print("What is your current age?")
    start = int(input()); virgintest = True
    retire = 59
    workingLife = retire - start
    print("What is your current salary?")
    salary = int(input())
    print("What is your current networth?")
    networth = int(input())
    postTaxSavings = (salary * 0.2)


    for i in range(workingLife + 1):
        networth = (networth * 1.07) + postTaxSavings
        round_networth = (int)(networth//1) #round(networth, 2) #2 decimal places
        networth_string = ('{0:,}'.format(round_networth))

        print(str(start) + "  net_worth: " + networth_string, end ="")
        if (virgintest == True and networth >= 1000000): 
            virgintest = False
            print("| You hit 1 million!") #\033[A  # \033[F
        else:
            print('') # print(), no longer works https://stackoverflow.com/questions/35248570/using-print-with-a-comma-in-python-3
        start += 1







if __name__ == '__main__':
    main()