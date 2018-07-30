## for demo
for /f %%i in (text.txt) do ....

for /f %%i in ('command') do .....

for /f "delims="%%i in ('command') do ..... "delims=" means do not spilit string

for /f "delims=, tokens=1"%%i in ('command') do ..... "delims=， tokens=1" means spilit string with "," and chose the first column
## find service status in windows
sc query servicesname

net start
## config service
sc config servicename obj= username password= xxxxxx

sc config servicename obj= LocalSysem
## wait
ping 127.0.0.1 -n 1 > null
## delete folder
rd /s /q folder
## delete file
del /f /s /q filename

if you set filename to a folder, it will delete all files under folder.

if you set filename to a real filename, xxxx.txt, it will delete this file.
