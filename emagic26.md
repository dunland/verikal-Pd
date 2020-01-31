# EMagic a 2 6 debugging

firmware errors when connecting to usb 3...

possible solutions:

- connect to usb 2
- get firmware patch from here: http://linux-kernel.2935.n7.nabble.com/PATCH-eMagic-emi26-and-emi62m-new-firmware-versions-td536003.html
- or from here: http://lkml.iu.edu/hypermail/linux/kernel/0605.0/0820.html


## session mit jelko 2020-01-27

### helpful links:
- https://radioangrezi.gitbook.io/studio/server
- https://jackaudio.org/faq/multiple_devices.html
- https://wiki.ubuntuusers.de/.asoundrc/

### complete log from session with jelko:

``` bash
dav@dav-ubu:~$ ps aux | grep pulse
dav       1898  0.0  0.1 2102844 18072 ?       Sl   Jan26   1:11 /usr/bin/pulseaudio --start --log-target=syslog
dav       2377  0.0  0.0 115820  4540 ?        S    Jan26   0:00 /usr/lib/x86_64-linux-gnu/pulse/gconf-helper
dav      19856  0.0  0.0  16832  1036 pts/2    S+   21:08   0:00 grep --color=auto pulse
dav@dav-ubu:~$ kill 1898
dav@dav-ubu:~$ ps aux | grep pulse
dav      19861  1.0  0.0 161364  3636 ?        S    21:08   0:00 /usr/bin/pulseaudio --start --log-target=syslog
dav      19862  2.0  0.0 161364  3660 ?        Sl   21:08   0:00 /usr/bin/pulseaudio --start --log-target=syslog
dav      19863  1.0  0.0 161364  3712 ?        Sl   21:08   0:00 /usr/bin/pulseaudio --start --log-target=syslog
dav      19867  0.0  0.0 161364   680 ?        Ss   21:08   0:00 /usr/bin/pulseaudio --start --log-target=syslog
dav      19868  5.0  0.0 255860 10052 ?        D<   21:08   0:00 /usr/bin/pulseaudio --start --log-target=syslog
dav      19869  0.0  0.0 161364  3644 ?        Sl   21:08   0:00 /usr/bin/pulseaudio --start --log-target=syslog
dav      19872  0.0  0.0  16832  1048 pts/2    S+   21:08   0:00 grep --color=auto pulse
dav@dav-ubu:~$ pulseaudio --help
pulseaudio [options]

COMMANDS:
  -h, --help                            Show this help
      --version                         Show version
      --dump-conf                       Dump default configuration
      --dump-modules                    Dump list of available modules
      --dump-resample-methods           Dump available resample methods
      --cleanup-shm                     Cleanup stale shared memory segments
      --start                           Start the daemon if it is not running
  -k  --kill                            Kill a running daemon
      --check                           Check for a running daemon (only returns exit code)

OPTIONS:
      --system[=BOOL]                   Run as system-wide instance
  -D, --daemonize[=BOOL]                Daemonize after startup
      --fail[=BOOL]                     Quit when startup fails
      --high-priority[=BOOL]            Try to set high nice level
                                        (only available as root, when SUID or
                                        with elevated RLIMIT_NICE)
      --realtime[=BOOL]                 Try to enable realtime scheduling
                                        (only available as root, when SUID or
                                        with elevated RLIMIT_RTPRIO)
      --disallow-module-loading[=BOOL]  Disallow module user requested module
                                        loading/unloading after startup
      --disallow-exit[=BOOL]            Disallow user requested exit
      --exit-idle-time=SECS             Terminate the daemon when idle and this
                                        time passed
      --scache-idle-time=SECS           Unload autoloaded samples when idle and
                                        this time passed
      --log-level[=LEVEL]               Increase or set verbosity level
  -v  --verbose                         Increase the verbosity level
      --log-target={auto,syslog,stderr,file:PATH,newfile:PATH}
                                        Specify the log target
      --log-meta[=BOOL]                 Include code location in log messages
      --log-time[=BOOL]                 Include timestamps in log messages
      --log-backtrace=FRAMES            Include a backtrace in log messages
  -p, --dl-search-path=PATH             Set the search path for dynamic shared
                                        objects (plugins)
      --resample-method=METHOD          Use the specified resampling method
                                        (See --dump-resample-methods for
                                        possible values)
      --use-pid-file[=BOOL]             Create a PID file
      --no-cpu-limit[=BOOL]             Do not install CPU load limiter on
                                        platforms that support it.
      --disable-shm[=BOOL]              Disable shared memory support.
      --enable-memfd[=BOOL]             Enable memfd shared memory support.

STARTUP SCRIPT:
  -L, --load="MODULE ARGUMENTS"         Load the specified plugin module with
                                        the specified argument
  -F, --file=FILENAME                   Run the specified script
  -C                                    Open a command line on the running TTY
                                        after startup

  -n                                    Don't load default script file
dav@dav-ubu:~$ pulseaudio --kill
dav@dav-ubu:~$ ps aux | grep pulse
dav      19902  2.0  0.0 161364  3648 ?        S    21:09   0:00 /usr/bin/pulseaudio --start --log-target=syslog
dav      19904  0.0  0.0 161364   676 ?        Ss   21:09   0:00 /usr/bin/pulseaudio --start --log-target=syslog
dav      19905  7.0  0.0 255860  9868 ?        D<   21:09   0:00 /usr/bin/pulseaudio --start --log-target=syslog
dav      19908  0.0  0.0  16832  1036 pts/2    S+   21:09   0:00 grep --color=auto pulse
dav@dav-ubu:~$ pulseaudio --kill
dav@dav-ubu:~$ ps aux | grep pulse
dav      19926  1.0  0.0 161364  3576 ?        S    21:09   0:00 /usr/bin/pulseaudio --start --log-target=syslog
dav      19928  0.0  0.0 161364   672 ?        Ss   21:09   0:00 /usr/bin/pulseaudio --start --log-target=syslog
dav      19929  3.0  0.0 255588  9200 ?        R<   21:09   0:00 /usr/bin/pulseaudio --start --log-target=syslog
dav      19931  0.0  0.0  16832  1048 pts/2    S+   21:09   0:00 grep --color=auto pulse
dav@dav-ubu:~$ echo autospawn = no > $HOME/.config/pulse/client.conf
dav@dav-ubu:~$ pulseaudio --kill
dav@dav-ubu:~$ ps aux | grep pulse
dav      19976  0.0  0.0  16832  1044 pts/2    S+   21:10   0:00 grep --color=auto pulse
dav@dav-ubu:~$ ps aux | grep pulse
dav      19986  0.0  0.0  16832  1096 pts/2    S+   21:10   0:00 grep --color=auto pulse
dav@dav-ubu:~$ #!/bin/sh
dav@dav-ubu:~$ for i in /proc/[0-9]*/fd/*
> do
>     echo ${i%/fd/*} $(readlink $i)
> done | grep -q /dev/snd/pcm
dav@dav-ubu:~$ pulseaudio --start
dav@dav-ubu:~$ ps aux | grep pulse
dav      22362  1.8  0.0 1563932 12104 ?       S<l  21:14   0:00 pulseaudio --start
dav      22369  0.0  0.0 115820  4600 ?        S    21:14   0:00 /usr/lib/x86_64-linux-gnu/pulse/gconf-helper
dav      22373  0.0  0.0  16832  1036 pts/2    S+   21:14   0:00 grep --color=auto pulse
dav@dav-ubu:~$ for i in /proc/[0-9]*/fd/*; do     echo ${i%/fd/*} $(readlink $i); done | grep -q /dev/snd/pcm
dav@dav-ubu:~$ fuser -v /dev/snd/*
                     USER        PID ACCESS COMMAND
/dev/snd/controlC0:  dav       22362 F.... pulseaudio
/dev/snd/controlC1:  dav       22362 F.... pulseaudio
/dev/snd/controlC2:  dav       22362 F.... pulseaudio
/dev/snd/seq:        dav       18963 F.... qjackctl
dav@dav-ubu:~$ pulseaudio --kill
dav@dav-ubu:~$ fuser -v /dev/snd/*
                     USER        PID ACCESS COMMAND
/dev/snd/seq:        dav       18963 F.... qjackctl
dav@dav-ubu:~$ fuser -v /dev/snd/*
                     USER        PID ACCESS COMMAND
/dev/snd/controlC0:  dav       19559 F.... jackdbus
/dev/snd/pcmC0D0c:   dav       19559 F...m jackdbus
/dev/snd/pcmC0D0p:   dav       19559 F...m jackdbus
/dev/snd/seq:        dav       18963 F.... qjackctl
dav@dav-ubu:~$ speaker-test -Dhw:0,0

speaker-test 1.1.3

Playback device is hw:0,0
Stream parameters are 48000Hz, S16_LE, 1 channels
Using 16 octaves of pink noise
Channels count (1) not available for playbacks: Invalid argument
Setting of hwparams failed: Invalid argument
dav@dav-ubu:~$ speaker-test -Dhw:0

speaker-test 1.1.3

Playback device is hw:0
Stream parameters are 48000Hz, S16_LE, 1 channels
Using 16 octaves of pink noise
Channels count (1) not available for playbacks: Invalid argument
Setting of hwparams failed: Invalid argument
dav@dav-ubu:~$ speaker-test -D hw:0

speaker-test 1.1.3

Playback device is hw:0
Stream parameters are 48000Hz, S16_LE, 1 channels
Using 16 octaves of pink noise
Channels count (1) not available for playbacks: Invalid argument
Setting of hwparams failed: Invalid argument
dav@dav-ubu:~$ speaker-test -D 0

speaker-test 1.1.3

Playback device is 0
Stream parameters are 48000Hz, S16_LE, 1 channels
Using 16 octaves of pink noise
ALSA lib pcm.c:2495:(snd_pcm_open_noupdate) Unknown PCM 0
Playback open error: -2,No such file or directory
dav@dav-ubu:~$ speaker-test -D 0,0

speaker-test 1.1.3

Playback device is 0,0
Stream parameters are 48000Hz, S16_LE, 1 channels
Using 16 octaves of pink noise
ALSA lib pcm.c:2495:(snd_pcm_open_noupdate) Unknown PCM 0,0
Playback open error: -2,No such file or directory
dav@dav-ubu:~$ speaker-test 

speaker-test 1.1.3

Playback device is default
Stream parameters are 48000Hz, S16_LE, 1 channels
Using 16 octaves of pink noise
Rate set to 48000Hz (requested 48000Hz)
Buffer size range from 2048 to 8192
Period size range from 1024 to 1024
Using max buffer size 8192
Periods = 4
was set period_size = 1024
was set buffer_size = 8192
 0 - Front Left
Time per period = 2,821601
 0 - Front Left
Time per period = 2,986502
 0 - Front Left
Time per period = 2,986487
 0 - Front Left
Time per period = 2,986918
 0 - Front Left
Time per period = 2,986943
 0 - Front Left
Time per period = 2,985921
 0 - Front Left
Time per period = 2,987361
 0 - Front Left
Time per period = 2,986340
 0 - Front Left
Time per period = 2,986309
 0 - Front Left
Time per period = 2,986519
 0 - Front Left
Time per period = 2,987090
 0 - Front Left
^CWrite error: -4,Interrupted system call
xrun_recovery failed: -4,Interrupted system call
Transfer failed: Interrupted system call
dav@dav-ubu:~$ aplay
Usage: aplay [OPTION]... [FILE]...

-h, --help              help
    --version           print current version
-l, --list-devices      list all soundcards and digital audio devices
-L, --list-pcms         list device names
-D, --device=NAME       select PCM by name
-q, --quiet             quiet mode
-t, --file-type TYPE    file type (voc, wav, raw or au)
-c, --channels=#        channels
-f, --format=FORMAT     sample format (case insensitive)
-r, --rate=#            sample rate
-d, --duration=#        interrupt after # seconds
-M, --mmap              mmap stream
-N, --nonblock          nonblocking mode
-F, --period-time=#     distance between interrupts is # microseconds
-B, --buffer-time=#     buffer duration is # microseconds
    --period-size=#     distance between interrupts is # frames
    --buffer-size=#     buffer duration is # frames
-A, --avail-min=#       min available space for wakeup is # microseconds
-R, --start-delay=#     delay for automatic PCM start is # microseconds 
                        (relative to buffer size if <= 0)
-T, --stop-delay=#      delay for automatic PCM stop is # microseconds from xrun
-v, --verbose           show PCM structure and setup (accumulative)
-V, --vumeter=TYPE      enable VU meter (TYPE: mono or stereo)
-I, --separate-channels one file for each channel
-i, --interactive       allow interactive operation from stdin
-m, --chmap=ch1,ch2,..  Give the channel map to override or follow
    --disable-resample  disable automatic rate resample
    --disable-channels  disable automatic channel conversions
    --disable-format    disable automatic format conversions
    --disable-softvol   disable software volume control (softvol)
    --test-position     test ring buffer position
    --test-coef=#       test coefficient for ring buffer position (default 8)
                        expression for validation is: coef * (buffer_size / 2)
    --test-nowait       do not wait for ring buffer - eats whole CPU
    --max-file-time=#   start another output file when the old file has recorded
                        for this many seconds
    --process-id-file   write the process ID here
    --use-strftime      apply the strftime facility to the output file name
    --dump-hw-params    dump hw_params of the device
    --fatal-errors      treat all errors as fatal
Recognized sample formats are: S8 U8 S16_LE S16_BE U16_LE U16_BE S24_LE S24_BE U24_LE U24_BE S32_LE S32_BE U32_LE U32_BE FLOAT_LE FLOAT_BE FLOAT64_LE FLOAT64_BE IEC958_SUBFRAME_LE IEC958_SUBFRAME_BE MU_LAW A_LAW IMA_ADPCM MPEG GSM SPECIAL S24_3LE S24_3BE U24_3LE U24_3BE S20_3LE S20_3BE U20_3LE U20_3BE S18_3LE S18_3BE U18_3LE U18_3BE G723_24 G723_24_1B G723_40 G723_40_1B DSD_U8 DSD_U16_LE DSD_U32_LE DSD_U16_BE DSD_U32_BE
Some of these may not be available on selected hardware
The available format shortcuts are:
-f cd (16 bit little endian, 44100, stereo)
-f cdr (16 bit big endian, 44100, stereo)
-f dat (16 bit little endian, 48000, stereo)
dav@dav-ubu:~$ aplay -l
**** List of PLAYBACK Hardware Devices ****
card 0: PCH [HDA Intel PCH], device 0: ALC269VC Analog [ALC269VC Analog]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 1: NVidia [HDA NVidia], device 3: HDMI 0 [HDMI 0]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 1: NVidia [HDA NVidia], device 7: HDMI 1 [HDMI 1]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 1: NVidia [HDA NVidia], device 8: HDMI 2 [HDMI 2]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 2: E26 [Emagic EMI 2|6], device 0: USB Audio [USB Audio]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
dav@dav-ubu:~$ speaker-test --hep

speaker-test 1.1.3

speaker-test: unrecognized option '--hep'
Unknown option '?'
dav@dav-ubu:~$ speaker-test --help

speaker-test 1.1.3

Usage: speaker-test [OPTION]... 
-h,--help	help
-D,--device	playback device
-r,--rate	stream rate in Hz
-c,--channels	count of channels in stream
-f,--frequency	sine wave frequency in Hz
-F,--format	sample format
-b,--buffer	ring buffer size in us
-p,--period	period size in us
-P,--nperiods	number of periods
-t,--test	pink=use pink noise, sine=use sine wave, wav=WAV file
-l,--nloops	specify number of loops to test, 0 = infinite
-s,--speaker	single speaker test. Values 1=Left, 2=right, etc
-w,--wavfile	Use the given WAV file as a test sound
-W,--wavdir	Specify the directory containing WAV files
-m,--chmap	Specify the channel map to override
-X,--force-frequency	force frequencies outside the 30-8000hz range
-S,--scale	Scale of generated test tones in percent (default=80)

Recognized sample formats are: S8 S16_LE S16_BE FLOAT_LE S32_LE S32_BE

dav@dav-ubu:~$ man speaker-test 
dav@dav-ubu:~$ speaker-test -Dplug:0,0

speaker-test 1.1.3

Playback device is plug:0,0
Stream parameters are 48000Hz, S16_LE, 1 channels
Using 16 octaves of pink noise
ALSA lib conf.c:4858:(parse_args) Unknown parameter 1
ALSA lib conf.c:4991:(snd_config_expand) Parse arguments error: No such file or directory
ALSA lib pcm.c:2495:(snd_pcm_open_noupdate) Unknown PCM plug:0,0
Playback open error: -2,No such file or directory
dav@dav-ubu:~$ speaker-test -Dhw:0,0

speaker-test 1.1.3

Playback device is hw:0,0
Stream parameters are 48000Hz, S16_LE, 1 channels
Using 16 octaves of pink noise
Channels count (1) not available for playbacks: Invalid argument
Setting of hwparams failed: Invalid argument
dav@dav-ubu:~$ speaker-test -Dhw:0,0 -c 2

speaker-test 1.1.3

Playback device is hw:0,0
Stream parameters are 48000Hz, S16_LE, 2 channels
Using 16 octaves of pink noise
Rate set to 48000Hz (requested 48000Hz)
Buffer size range from 64 to 16384
Period size range from 32 to 8192
Using max buffer size 16384
Periods = 4
was set period_size = 4096
was set buffer_size = 16384
 0 - Front Left
 1 - Front Right
Time per period = 5,632104
 0 - Front Left
^C 1 - Front Right
Time per period = 1,912432
dav@dav-ubu:~$ speaker-test -Dhw:2,0 -c 2

speaker-test 1.1.3

Playback device is hw:2,0
Stream parameters are 48000Hz, S16_LE, 2 channels
Using 16 octaves of pink noise
Rate set to 48000Hz (requested 48000Hz)
Buffer size range from 96 to 262144
Period size range from 48 to 131072
Using max buffer size 262144
Periods = 4
was set period_size = 65536
was set buffer_size = 262144
 0 - Front Left
 1 - Front Right
Time per period = 5,888280
 0 - Front Left
^C 1 - Front Right
Time per period = 0,894230
dav@dav-ubu:~$ speaker-test -Dhw:2,0 -c 6

speaker-test 1.1.3

Playback device is hw:2,0
Stream parameters are 48000Hz, S16_LE, 6 channels
Using 16 octaves of pink noise
Rate set to 48000Hz (requested 48000Hz)
Buffer size range from 96 to 87381
Period size range from 48 to 43690
Using max buffer size 87380
Periods = 4
was set period_size = 21845
was set buffer_size = 87380
 0 - Front Left
 2 - Front Center
 1 - Front Right
 5 - Side Right
 4 - Side Left
 3 - LFE
Time per period = 14,568446
 0 - Front Left
 2 - Front Center
 1 - Front Right
 5 - Side Right
 4 - Side Left
 3 - LFE
Time per period = 16,382997
 0 - Front Left
^C 2 - Front Center
 1 - Front Right
 5 - Side Right
 4 - Side Left
 3 - LFE
Time per period = 0,576192
dav@dav-ubu:~$ aplay -l
**** List of PLAYBACK Hardware Devices ****
card 0: PCH [HDA Intel PCH], device 0: ALC269VC Analog [ALC269VC Analog]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 1: NVidia [HDA NVidia], device 3: HDMI 0 [HDMI 0]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 1: NVidia [HDA NVidia], device 7: HDMI 1 [HDMI 1]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 1: NVidia [HDA NVidia], device 8: HDMI 2 [HDMI 2]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 2: E26 [Emagic EMI 2|6], device 0: USB Audio [USB Audio]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
dav@dav-ubu:~$ fuser -v /dev/snd/*
                     USER        PID ACCESS COMMAND
/dev/snd/controlC0:  dav       24797 F.... purr-data
/dev/snd/controlC1:  dav       24797 F.... purr-data
/dev/snd/controlC2:  dav       24797 F.... purr-data
/dev/snd/seq:        dav       24894 F.... qjackctl
dav@dav-ubu:~$ fuser -v /dev/snd/*
                     USER        PID ACCESS COMMAND
/dev/snd/seq:        dav       24894 F.... qjackctl
dav@dav-ubu:~$ killall -9 jack
jack: no process found
dav@dav-ubu:~$ fuser -v /dev/snd/*
                     USER        PID ACCESS COMMAND
/dev/snd/seq:        dav       24894 F.... qjackctl
dav@dav-ubu:~$ fuser -v /dev/snd/*
                     USER        PID ACCESS COMMAND
/dev/snd/seq:        dav       24894 F.... qjackctl
dav@dav-ubu:~$ killall -9 qjackctl 
dav@dav-ubu:~$ fuser -v /dev/snd/*
dav@dav-ubu:~$ fuser -v /dev/snd/*
                     USER        PID ACCESS COMMAND
/dev/snd/seq:        dav       25122 F.... qjackctl
dav@dav-ubu:~$ killall -9 jackdbus
dav@dav-ubu:~$ speaker-test -Dhw:2,0 -c 6

speaker-test 1.1.3

Playback device is hw:2,0
Stream parameters are 48000Hz, S16_LE, 6 channels
Using 16 octaves of pink noise
Rate set to 48000Hz (requested 48000Hz)
Buffer size range from 96 to 87381
Period size range from 48 to 43690
Using max buffer size 87380
Periods = 4
was set period_size = 21845
was set buffer_size = 87380
 0 - Front Left
 2 - Front Center
^C 1 - Front Right
 5 - Side Right
 4 - Side Left
 3 - LFE
Time per period = 3,019295
dav@dav-ubu:~$ speaker-test -Dhw:2,0 -c 6

speaker-test 1.1.3

Playback device is hw:2,0
Stream parameters are 48000Hz, S16_LE, 6 channels
Using 16 octaves of pink noise
Rate set to 48000Hz (requested 48000Hz)
Buffer size range from 96 to 87381
Period size range from 48 to 43690
Using max buffer size 87380
Periods = 4
was set period_size = 21845
was set buffer_size = 87380
 0 - Front Left
 2 - Front Center
 1 - Front Right
 5 - Side Right
 4 - Side Left
 3 - LFE
^CTime per period = 13,228375
dav@dav-ubu:~$ speaker-test -Dhw:2,0 -c 6 --help

speaker-test 1.1.3

Usage: speaker-test [OPTION]... 
-h,--help	help
-D,--device	playback device
-r,--rate	stream rate in Hz
-c,--channels	count of channels in stream
-f,--frequency	sine wave frequency in Hz
-F,--format	sample format
-b,--buffer	ring buffer size in us
-p,--period	period size in us
-P,--nperiods	number of periods
-t,--test	pink=use pink noise, sine=use sine wave, wav=WAV file
-l,--nloops	specify number of loops to test, 0 = infinite
-s,--speaker	single speaker test. Values 1=Left, 2=right, etc
-w,--wavfile	Use the given WAV file as a test sound
-W,--wavdir	Specify the directory containing WAV files
-m,--chmap	Specify the channel map to override
-X,--force-frequency	force frequencies outside the 30-8000hz range
-S,--scale	Scale of generated test tones in percent (default=80)

Recognized sample formats are: S8 S16_LE S16_BE FLOAT_LE S32_LE S32_BE

dav@dav-ubu:~$ speaker-test -Dhw:2,0 -c 6 --rate 44100

speaker-test 1.1.3

Playback device is hw:2,0
Stream parameters are 44100Hz, S16_LE, 6 channels
Using 16 octaves of pink noise
Rate set to 44100Hz (requested 44100Hz)
Buffer size range from 90 to 87381
Period size range from 45 to 43690
Using max buffer size 87380
Periods = 4
was set period_size = 21845
was set buffer_size = 87380
 0 - Front Left
 2 - Front Center
 1 - Front Right
^C 5 - Side Right
 4 - Side Left
 3 - LFE
Time per period = 4,896817
dav@dav-ubu:~$ speaker-test -Dhw:2,0 -c 6 --rate 44100 --period 1024

speaker-test 1.1.3

Playback device is hw:2,0
Stream parameters are 44100Hz, S16_LE, 6 channels
Using 16 octaves of pink noise
Rate set to 44100Hz (requested 44100Hz)
Buffer size range from 90 to 87381
Period size range from 45 to 43690
Requested period time 1024 us
Periods = 4
was set period_size = 45
was set buffer_size = 180
 0 - Front Left
 2 - Front Center
^C 1 - Front Right
 5 - Side Right
 4 - Side Left
 3 - LFE
Time per period = 3,085397
dav@dav-ubu:~$ speaker-test -Dhw:2,0 -c 6 --rate 44100 --period 1024 -P 4

speaker-test 1.1.3

Playback device is hw:2,0
Stream parameters are 44100Hz, S16_LE, 6 channels
Using 16 octaves of pink noise
Rate set to 44100Hz (requested 44100Hz)
Buffer size range from 90 to 87381
Period size range from 45 to 43690
Requested period time 1024 us
Periods = 4
was set period_size = 45
was set buffer_size = 180
 0 - Front Left
^C 2 - Front Center
 1 - Front Right
 5 - Side Right
 4 - Side Left
 3 - LFE
Time per period = 1,465692
dav@dav-ubu:~$ speaker-test -Dhw:2,0 -c 6^C-rate 44100 --period 1024 -P 4
dav@dav-ubu:~$ aplay -l
**** List of PLAYBACK Hardware Devices ****
card 0: PCH [HDA Intel PCH], device 0: ALC269VC Analog [ALC269VC Analog]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 1: NVidia [HDA NVidia], device 3: HDMI 0 [HDMI 0]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 1: NVidia [HDA NVidia], device 7: HDMI 1 [HDMI 1]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 1: NVidia [HDA NVidia], device 8: HDMI 2 [HDMI 2]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 2: E26 [Emagic EMI 2|6], device 0: USB Audio [USB Audio]
  Subdevices: 0/1
  Subdevice #0: subdevice #0
dav@dav-ubu:~$ cat .a
.adobe/         .anyconnect     .arduino15/     .atom/          .audacity-data/
dav@dav-ubu:~$ cat .a
.adobe/         .anyconnect     .arduino15/     .atom/          .audacity-data/
dav@dav-ubu:~$ cat .asoundrc 
pcm.E26_1_0 {
        type plug
        slave.pcm {
        type dshare
        ipc_key 123456
        slave.pcm "hw:3"
        slave.channels 6
        bindings.0 0
        }   
}

pcm.E26_1_1 {
        type plug
        slave.pcm {
        type dshare
        ipc_key 123456
        slave.pcm "hw:3"
        slave.channels 6
        bindings.0 1
        }   
}

pcm.E26_1_2 {
        type plug
        slave.pcm {
        type dshare
        ipc_key 123456
        slave.pcm "hw:3"
        slave.channels 6
        bindings.0 2
        }   
}

pcm.E26_1_3 {
        type plug
        slave.pcm {
        type dshare
        ipc_key 123456
        slave.pcm "hw:3"
        slave.channels 6
        bindings.0 3
        }   
}

pcm.E26_1_4 {
        type plug
        slave.pcm {
        type dshare
        ipc_key 123456
        slave.pcm "hw:3"
        slave.channels 6
        bindings.0 4
        }   
}

pcm.E26_1_5 {
        type plug
        slave.pcm {
        type dshare
        ipc_key 123456
        slave.pcm "hw:3"
        slave.channels 6
        bindings.0 5
        }   
}dav@dav-ubu:~$ aplay -l
**** List of PLAYBACK Hardware Devices ****
card 0: PCH [HDA Intel PCH], device 0: ALC269VC Analog [ALC269VC Analog]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 1: NVidia [HDA NVidia], device 3: HDMI 0 [HDMI 0]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 1: NVidia [HDA NVidia], device 7: HDMI 1 [HDMI 1]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 1: NVidia [HDA NVidia], device 8: HDMI 2 [HDMI 2]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 2: E26 [Emagic EMI 2|6], device 0: USB Audio [USB Audio]
  Subdevices: 0/1
  Subdevice #0: subdevice #0
dav@dav-ubu:~$ ge
gedit        gencfu       genl         gerbview     getfattr     gettextize
gemtopbm     gencmn       gennorm2     getcap       getkeycodes  gettext.sh
gemtopnm     gencnval     genrb        getconf      getopt       getty
genbrk       gendict      gensprep     geteltorito  getopts      getweb
gencat       gendiff      geod         getent       getpcaps     
genccode     genisoimage  geqn         getfacl      gettext      
dav@dav-ubu:~$ gedit .asoundrc &
[1] 25414
dav@dav-ubu:~$ pwd
/home/dav
[1]+  Done                    gedit .asoundrc
dav@dav-ubu:~$ alsa
alsa          alsactl       alsaloop      alsatplg      
alsabat       alsa_in       alsamixer     alsaucm       
alsabat-test  alsa-info     alsa_out      
dav@dav-ubu:~$ alsa
alsa          alsactl       alsaloop      alsatplg      
alsabat       alsa_in       alsamixer     alsaucm       
alsabat-test  alsa-info     alsa_out      
dav@dav-ubu:~$ alsactl restart
alsactl: Unknown command 'restart'...
dav@dav-ubu:~$ alsactl 
alsactl: Specify command...
dav@dav-ubu:~$ alsactl help
alsactl: Unknown command 'help'...
dav@dav-ubu:~$ man alsactl
dav@dav-ubu:~$ alsactl --help
Usage: alsactl <options> command

global options:
  -h,--help        this help
  -d,--debug       debug mode
  -v,--version     print version of this program

Available state options:
  -f,--file #      configuration file (default /var/lib/alsa/asound.state)
  -l,--lock        use file locking to serialize concurrent access
  -L,--no-lock     do not use file locking to serialize concurrent access
  -O,--lock-state-file #  state lock file path (default /var/lock/asound.state.lock)
  -F,--force       try to restore the matching controls as much as possible
                     (default mode)
  -g,--ignore      ignore 'No soundcards found' error
  -P,--pedantic    do not restore mismatching controls (old default)
  -I,--no-init-fallback  
                   don't initialize even if restore fails
  -r,--runstate #  save restore and init state to this file (only errors)
                     default settings is 'no file set'
  -R,--remove      remove runstate file at first, otherwise append errors
  -p,--period #    store period in seconds for the daemon command
  -e,--pid-file #  pathname for the process id (daemon mode)

Available init options:
  -E,--env #=#     set environment variable for init phase (NAME=VALUE)
  -i,--initfile #  main configuration file for init phase
                     (default /usr/share/alsa/init/00main)
  -b,--background  run daemon in background
  -s,--syslog      use syslog for messages
  -n,--nice #      set the process priority (see 'man nice')
  -c,--sched-idle  set the process scheduling policy to idle (SCHED_IDLE)

Available commands:
  store     <card>  save current driver setup for one or each soundcards
                      to configuration file
  restore   <card>  load current driver setup for one or each soundcards
                      from configuration file
  nrestore  <card>  like restore, but notify the daemon to rescan soundcards
  init      <card>  initialize driver to a default state
  daemon    <card>  store state periodically for one or each soundcards
  rdaemon   <card>  like daemon but do the state restore at first
  kill      <cmd>   notify daemon to quit, rescan or save_and_quit
  monitor   <card>  monitor control events
dav@dav-ubu:~$ aplay -la
aplay: invalid option -- 'a'
Try `aplay --help' for more information.
dav@dav-ubu:~$ aplay --help
Usage: aplay [OPTION]... [FILE]...

-h, --help              help
    --version           print current version
-l, --list-devices      list all soundcards and digital audio devices
-L, --list-pcms         list device names
-D, --device=NAME       select PCM by name
-q, --quiet             quiet mode
-t, --file-type TYPE    file type (voc, wav, raw or au)
-c, --channels=#        channels
-f, --format=FORMAT     sample format (case insensitive)
-r, --rate=#            sample rate
-d, --duration=#        interrupt after # seconds
-M, --mmap              mmap stream
-N, --nonblock          nonblocking mode
-F, --period-time=#     distance between interrupts is # microseconds
-B, --buffer-time=#     buffer duration is # microseconds
    --period-size=#     distance between interrupts is # frames
    --buffer-size=#     buffer duration is # frames
-A, --avail-min=#       min available space for wakeup is # microseconds
-R, --start-delay=#     delay for automatic PCM start is # microseconds 
                        (relative to buffer size if <= 0)
-T, --stop-delay=#      delay for automatic PCM stop is # microseconds from xrun
-v, --verbose           show PCM structure and setup (accumulative)
-V, --vumeter=TYPE      enable VU meter (TYPE: mono or stereo)
-I, --separate-channels one file for each channel
-i, --interactive       allow interactive operation from stdin
-m, --chmap=ch1,ch2,..  Give the channel map to override or follow
    --disable-resample  disable automatic rate resample
    --disable-channels  disable automatic channel conversions
    --disable-format    disable automatic format conversions
    --disable-softvol   disable software volume control (softvol)
    --test-position     test ring buffer position
    --test-coef=#       test coefficient for ring buffer position (default 8)
                        expression for validation is: coef * (buffer_size / 2)
    --test-nowait       do not wait for ring buffer - eats whole CPU
    --max-file-time=#   start another output file when the old file has recorded
                        for this many seconds
    --process-id-file   write the process ID here
    --use-strftime      apply the strftime facility to the output file name
    --dump-hw-params    dump hw_params of the device
    --fatal-errors      treat all errors as fatal
Recognized sample formats are: S8 U8 S16_LE S16_BE U16_LE U16_BE S24_LE S24_BE U24_LE U24_BE S32_LE S32_BE U32_LE U32_BE FLOAT_LE FLOAT_BE FLOAT64_LE FLOAT64_BE IEC958_SUBFRAME_LE IEC958_SUBFRAME_BE MU_LAW A_LAW IMA_ADPCM MPEG GSM SPECIAL S24_3LE S24_3BE U24_3LE U24_3BE S20_3LE S20_3BE U20_3LE U20_3BE S18_3LE S18_3BE U18_3LE U18_3BE G723_24 G723_24_1B G723_40 G723_40_1B DSD_U8 DSD_U16_LE DSD_U32_LE DSD_U16_BE DSD_U32_BE
Some of these may not be available on selected hardware
The available format shortcuts are:
-f cd (16 bit little endian, 44100, stereo)
-f cdr (16 bit big endian, 44100, stereo)
-f dat (16 bit little endian, 48000, stereo)
dav@dav-ubu:~$ aplay -L
null
    Discard all samples (playback) or generate zero samples (capture)
pulse
    PulseAudio Sound Server
E26_1_0
E26_1_1
E26_1_2
E26_1_3
E26_1_4
E26_1_5
default:CARD=PCH
    HDA Intel PCH, ALC269VC Analog
    Default Audio Device
sysdefault:CARD=PCH
    HDA Intel PCH, ALC269VC Analog
    Default Audio Device
front:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    Front speakers
surround21:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    2.1 Surround output to Front and Subwoofer speakers
surround40:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    4.0 Surround output to Front and Rear speakers
surround41:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    4.1 Surround output to Front, Rear and Subwoofer speakers
surround50:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    5.0 Surround output to Front, Center and Rear speakers
surround51:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    5.1 Surround output to Front, Center, Rear and Subwoofer speakers
surround71:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    7.1 Surround output to Front, Center, Side, Rear and Woofer speakers
dmix:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    Direct sample mixing device
dsnoop:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    Direct sample snooping device
hw:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    Direct hardware device without any conversions
plughw:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    Hardware device with all software conversions
hdmi:CARD=NVidia,DEV=0
    HDA NVidia, HDMI 0
    HDMI Audio Output
hdmi:CARD=NVidia,DEV=1
    HDA NVidia, HDMI 1
    HDMI Audio Output
hdmi:CARD=NVidia,DEV=2
    HDA NVidia, HDMI 2
    HDMI Audio Output
dmix:CARD=NVidia,DEV=3
    HDA NVidia, HDMI 0
    Direct sample mixing device
dmix:CARD=NVidia,DEV=7
    HDA NVidia, HDMI 1
    Direct sample mixing device
dmix:CARD=NVidia,DEV=8
    HDA NVidia, HDMI 2
    Direct sample mixing device
dsnoop:CARD=NVidia,DEV=3
    HDA NVidia, HDMI 0
    Direct sample snooping device
dsnoop:CARD=NVidia,DEV=7
    HDA NVidia, HDMI 1
    Direct sample snooping device
dsnoop:CARD=NVidia,DEV=8
    HDA NVidia, HDMI 2
    Direct sample snooping device
hw:CARD=NVidia,DEV=3
    HDA NVidia, HDMI 0
    Direct hardware device without any conversions
hw:CARD=NVidia,DEV=7
    HDA NVidia, HDMI 1
    Direct hardware device without any conversions
hw:CARD=NVidia,DEV=8
    HDA NVidia, HDMI 2
    Direct hardware device without any conversions
plughw:CARD=NVidia,DEV=3
    HDA NVidia, HDMI 0
    Hardware device with all software conversions
plughw:CARD=NVidia,DEV=7
    HDA NVidia, HDMI 1
    Hardware device with all software conversions
plughw:CARD=NVidia,DEV=8
    HDA NVidia, HDMI 2
    Hardware device with all software conversions
default:CARD=E26
    Emagic EMI 2
    Default Audio Device
sysdefault:CARD=E26
    Emagic EMI 2
    Default Audio Device
front:CARD=E26,DEV=0
    Emagic EMI 2
    Front speakers
surround21:CARD=E26,DEV=0
    Emagic EMI 2
    2.1 Surround output to Front and Subwoofer speakers
surround40:CARD=E26,DEV=0
    Emagic EMI 2
    4.0 Surround output to Front and Rear speakers
surround41:CARD=E26,DEV=0
    Emagic EMI 2
    4.1 Surround output to Front, Rear and Subwoofer speakers
surround50:CARD=E26,DEV=0
    Emagic EMI 2
    5.0 Surround output to Front, Center and Rear speakers
surround51:CARD=E26,DEV=0
    Emagic EMI 2
    5.1 Surround output to Front, Center, Rear and Subwoofer speakers
surround71:CARD=E26,DEV=0
    Emagic EMI 2
    7.1 Surround output to Front, Center, Side, Rear and Woofer speakers
iec958:CARD=E26,DEV=0
    Emagic EMI 2
    IEC958 (S/PDIF) Digital Audio Output
dmix:CARD=E26,DEV=0
    Emagic EMI 2
    Direct sample mixing device
dsnoop:CARD=E26,DEV=0
    Emagic EMI 2
    Direct sample snooping device
hw:CARD=E26,DEV=0
    Emagic EMI 2
    Direct hardware device without any conversions
plughw:CARD=E26,DEV=0
    Emagic EMI 2
    Hardware device with all software conversions
dav@dav-ubu:~$ aplay -l
**** List of PLAYBACK Hardware Devices ****
card 0: PCH [HDA Intel PCH], device 0: ALC269VC Analog [ALC269VC Analog]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 1: NVidia [HDA NVidia], device 3: HDMI 0 [HDMI 0]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 1: NVidia [HDA NVidia], device 7: HDMI 1 [HDMI 1]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 1: NVidia [HDA NVidia], device 8: HDMI 2 [HDMI 2]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 2: E26 [Emagic EMI 2|6], device 0: USB Audio [USB Audio]
  Subdevices: 0/1
  Subdevice #0: subdevice #0
dav@dav-ubu:~$ aplay -L
null
    Discard all samples (playback) or generate zero samples (capture)
pulse
    PulseAudio Sound Server
E26_1_0
E26_1_1
E26_1_2
E26_1_3
E26_1_4
E26_1_5
default:CARD=PCH
    HDA Intel PCH, ALC269VC Analog
    Default Audio Device
sysdefault:CARD=PCH
    HDA Intel PCH, ALC269VC Analog
    Default Audio Device
front:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    Front speakers
surround21:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    2.1 Surround output to Front and Subwoofer speakers
surround40:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    4.0 Surround output to Front and Rear speakers
surround41:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    4.1 Surround output to Front, Rear and Subwoofer speakers
surround50:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    5.0 Surround output to Front, Center and Rear speakers
surround51:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    5.1 Surround output to Front, Center, Rear and Subwoofer speakers
surround71:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    7.1 Surround output to Front, Center, Side, Rear and Woofer speakers
dmix:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    Direct sample mixing device
dsnoop:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    Direct sample snooping device
hw:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    Direct hardware device without any conversions
plughw:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    Hardware device with all software conversions
hdmi:CARD=NVidia,DEV=0
    HDA NVidia, HDMI 0
    HDMI Audio Output
hdmi:CARD=NVidia,DEV=1
    HDA NVidia, HDMI 1
    HDMI Audio Output
hdmi:CARD=NVidia,DEV=2
    HDA NVidia, HDMI 2
    HDMI Audio Output
dmix:CARD=NVidia,DEV=3
    HDA NVidia, HDMI 0
    Direct sample mixing device
dmix:CARD=NVidia,DEV=7
    HDA NVidia, HDMI 1
    Direct sample mixing device
dmix:CARD=NVidia,DEV=8
    HDA NVidia, HDMI 2
    Direct sample mixing device
dsnoop:CARD=NVidia,DEV=3
    HDA NVidia, HDMI 0
    Direct sample snooping device
dsnoop:CARD=NVidia,DEV=7
    HDA NVidia, HDMI 1
    Direct sample snooping device
dsnoop:CARD=NVidia,DEV=8
    HDA NVidia, HDMI 2
    Direct sample snooping device
hw:CARD=NVidia,DEV=3
    HDA NVidia, HDMI 0
    Direct hardware device without any conversions
hw:CARD=NVidia,DEV=7
    HDA NVidia, HDMI 1
    Direct hardware device without any conversions
hw:CARD=NVidia,DEV=8
    HDA NVidia, HDMI 2
    Direct hardware device without any conversions
plughw:CARD=NVidia,DEV=3
    HDA NVidia, HDMI 0
    Hardware device with all software conversions
plughw:CARD=NVidia,DEV=7
    HDA NVidia, HDMI 1
    Hardware device with all software conversions
plughw:CARD=NVidia,DEV=8
    HDA NVidia, HDMI 2
    Hardware device with all software conversions
default:CARD=E26
    Emagic EMI 2
    Default Audio Device
sysdefault:CARD=E26
    Emagic EMI 2
    Default Audio Device
front:CARD=E26,DEV=0
    Emagic EMI 2
    Front speakers
surround21:CARD=E26,DEV=0
    Emagic EMI 2
    2.1 Surround output to Front and Subwoofer speakers
surround40:CARD=E26,DEV=0
    Emagic EMI 2
    4.0 Surround output to Front and Rear speakers
surround41:CARD=E26,DEV=0
    Emagic EMI 2
    4.1 Surround output to Front, Rear and Subwoofer speakers
surround50:CARD=E26,DEV=0
    Emagic EMI 2
    5.0 Surround output to Front, Center and Rear speakers
surround51:CARD=E26,DEV=0
    Emagic EMI 2
    5.1 Surround output to Front, Center, Rear and Subwoofer speakers
surround71:CARD=E26,DEV=0
    Emagic EMI 2
    7.1 Surround output to Front, Center, Side, Rear and Woofer speakers
iec958:CARD=E26,DEV=0
    Emagic EMI 2
    IEC958 (S/PDIF) Digital Audio Output
dmix:CARD=E26,DEV=0
    Emagic EMI 2
    Direct sample mixing device
dsnoop:CARD=E26,DEV=0
    Emagic EMI 2
    Direct sample snooping device
hw:CARD=E26,DEV=0
    Emagic EMI 2
    Direct hardware device without any conversions
plughw:CARD=E26,DEV=0
    Emagic EMI 2
    Hardware device with all software conversions
dav@dav-ubu:~$ speaker-test -Dplug:E26_1_0

speaker-test 1.1.3

Playback device is plug:E26_1_0
Stream parameters are 48000Hz, S16_LE, 1 channels
Using 16 octaves of pink noise
ALSA lib pcm_dshare.c:737:(snd_pcm_dshare_open) unable to open slave
Playback open error: -16,Device or resource busy
dav@dav-ubu:~$ speaker-test -DE26_1_0

speaker-test 1.1.3

Playback device is E26_1_0
Stream parameters are 48000Hz, S16_LE, 1 channels
Using 16 octaves of pink noise
ALSA lib pcm_dshare.c:737:(snd_pcm_dshare_open) unable to open slave
Playback open error: -16,Device or resource busy
dav@dav-ubu:~$ speaker-test -Dplug:E26_1_0

speaker-test 1.1.3

Playback device is plug:E26_1_0
Stream parameters are 48000Hz, S16_LE, 1 channels
Using 16 octaves of pink noise
ALSA lib pcm_dshare.c:737:(snd_pcm_dshare_open) unable to open slave
Playback open error: -16,Device or resource busy
dav@dav-ubu:~$ speaker-test -Dhw:2,0 -c 6 --rate 44100 --period 1024 -P 4

speaker-test 1.1.3

Playback device is hw:2,0
Stream parameters are 44100Hz, S16_LE, 6 channels
Using 16 octaves of pink noise
Playback open error: -16,Device or resource busy
dav@dav-ubu:~$ speaker-test -Dhw:2,0 -c 6 --rate 44100 --period 1024 -P 4

speaker-test 1.1.3

Playback device is hw:2,0
Stream parameters are 44100Hz, S16_LE, 6 channels
Using 16 octaves of pink noise
Rate set to 44100Hz (requested 44100Hz)
Buffer size range from 90 to 87381
Period size range from 45 to 43690
Requested period time 1024 us
Periods = 4
was set period_size = 45
was set buffer_size = 180
 0 - Front Left
 2 - Front Center
^C 1 - Front Right
 5 - Side Right
 4 - Side Left
 3 - LFE
Time per period = 4,147331
dav@dav-ubu:~$ fuser -v /dev/snd/*^C
dav@dav-ubu:~$ mv .asoundrc asoundrc-map
dav@dav-ubu:~$ aplay -L
null
    Discard all samples (playback) or generate zero samples (capture)
pulse
    PulseAudio Sound Server
default:CARD=PCH
    HDA Intel PCH, ALC269VC Analog
    Default Audio Device
sysdefault:CARD=PCH
    HDA Intel PCH, ALC269VC Analog
    Default Audio Device
front:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    Front speakers
surround21:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    2.1 Surround output to Front and Subwoofer speakers
surround40:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    4.0 Surround output to Front and Rear speakers
surround41:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    4.1 Surround output to Front, Rear and Subwoofer speakers
surround50:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    5.0 Surround output to Front, Center and Rear speakers
surround51:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    5.1 Surround output to Front, Center, Rear and Subwoofer speakers
surround71:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    7.1 Surround output to Front, Center, Side, Rear and Woofer speakers
dmix:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    Direct sample mixing device
dsnoop:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    Direct sample snooping device
hw:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    Direct hardware device without any conversions
plughw:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    Hardware device with all software conversions
hdmi:CARD=NVidia,DEV=0
    HDA NVidia, HDMI 0
    HDMI Audio Output
hdmi:CARD=NVidia,DEV=1
    HDA NVidia, HDMI 1
    HDMI Audio Output
hdmi:CARD=NVidia,DEV=2
    HDA NVidia, HDMI 2
    HDMI Audio Output
dmix:CARD=NVidia,DEV=3
    HDA NVidia, HDMI 0
    Direct sample mixing device
dmix:CARD=NVidia,DEV=7
    HDA NVidia, HDMI 1
    Direct sample mixing device
dmix:CARD=NVidia,DEV=8
    HDA NVidia, HDMI 2
    Direct sample mixing device
dsnoop:CARD=NVidia,DEV=3
    HDA NVidia, HDMI 0
    Direct sample snooping device
dsnoop:CARD=NVidia,DEV=7
    HDA NVidia, HDMI 1
    Direct sample snooping device
dsnoop:CARD=NVidia,DEV=8
    HDA NVidia, HDMI 2
    Direct sample snooping device
hw:CARD=NVidia,DEV=3
    HDA NVidia, HDMI 0
    Direct hardware device without any conversions
hw:CARD=NVidia,DEV=7
    HDA NVidia, HDMI 1
    Direct hardware device without any conversions
hw:CARD=NVidia,DEV=8
    HDA NVidia, HDMI 2
    Direct hardware device without any conversions
plughw:CARD=NVidia,DEV=3
    HDA NVidia, HDMI 0
    Hardware device with all software conversions
plughw:CARD=NVidia,DEV=7
    HDA NVidia, HDMI 1
    Hardware device with all software conversions
plughw:CARD=NVidia,DEV=8
    HDA NVidia, HDMI 2
    Hardware device with all software conversions
default:CARD=E26
    Emagic EMI 2
    Default Audio Device
sysdefault:CARD=E26
    Emagic EMI 2
    Default Audio Device
front:CARD=E26,DEV=0
    Emagic EMI 2
    Front speakers
surround21:CARD=E26,DEV=0
    Emagic EMI 2
    2.1 Surround output to Front and Subwoofer speakers
surround40:CARD=E26,DEV=0
    Emagic EMI 2
    4.0 Surround output to Front and Rear speakers
surround41:CARD=E26,DEV=0
    Emagic EMI 2
    4.1 Surround output to Front, Rear and Subwoofer speakers
surround50:CARD=E26,DEV=0
    Emagic EMI 2
    5.0 Surround output to Front, Center and Rear speakers
surround51:CARD=E26,DEV=0
    Emagic EMI 2
    5.1 Surround output to Front, Center, Rear and Subwoofer speakers
surround71:CARD=E26,DEV=0
    Emagic EMI 2
    7.1 Surround output to Front, Center, Side, Rear and Woofer speakers
iec958:CARD=E26,DEV=0
    Emagic EMI 2
    IEC958 (S/PDIF) Digital Audio Output
dmix:CARD=E26,DEV=0
    Emagic EMI 2
    Direct sample mixing device
dsnoop:CARD=E26,DEV=0
    Emagic EMI 2
    Direct sample snooping device
hw:CARD=E26,DEV=0
    Emagic EMI 2
    Direct hardware device without any conversions
plughw:CARD=E26,DEV=0
    Emagic EMI 2
    Hardware device with all software conversions
dav@dav-ubu:~$ alsa
alsa          alsactl       alsaloop      alsatplg      
alsabat       alsa_in       alsamixer     alsaucm       
alsabat-test  alsa-info     alsa_out      
dav@dav-ubu:~$ alsactl 
alsactl: Specify command...
dav@dav-ubu:~$ 
dav@dav-ubu:~$ alsactl --help
Usage: alsactl <options> command

global options:
  -h,--help        this help
  -d,--debug       debug mode
  -v,--version     print version of this program

Available state options:
  -f,--file #      configuration file (default /var/lib/alsa/asound.state)
  -l,--lock        use file locking to serialize concurrent access
  -L,--no-lock     do not use file locking to serialize concurrent access
  -O,--lock-state-file #  state lock file path (default /var/lock/asound.state.lock)
  -F,--force       try to restore the matching controls as much as possible
                     (default mode)
  -g,--ignore      ignore 'No soundcards found' error
  -P,--pedantic    do not restore mismatching controls (old default)
  -I,--no-init-fallback  
                   don't initialize even if restore fails
  -r,--runstate #  save restore and init state to this file (only errors)
                     default settings is 'no file set'
  -R,--remove      remove runstate file at first, otherwise append errors
  -p,--period #    store period in seconds for the daemon command
  -e,--pid-file #  pathname for the process id (daemon mode)

Available init options:
  -E,--env #=#     set environment variable for init phase (NAME=VALUE)
  -i,--initfile #  main configuration file for init phase
                     (default /usr/share/alsa/init/00main)
  -b,--background  run daemon in background
  -s,--syslog      use syslog for messages
  -n,--nice #      set the process priority (see 'man nice')
  -c,--sched-idle  set the process scheduling policy to idle (SCHED_IDLE)

Available commands:
  store     <card>  save current driver setup for one or each soundcards
                      to configuration file
  restore   <card>  load current driver setup for one or each soundcards
                      from configuration file
  nrestore  <card>  like restore, but notify the daemon to rescan soundcards
  init      <card>  initialize driver to a default state
  daemon    <card>  store state periodically for one or each soundcards
  rdaemon   <card>  like daemon but do the state restore at first
  kill      <cmd>   notify daemon to quit, rescan or save_and_quit
  monitor   <card>  monitor control events
dav@dav-ubu:~$ alsa
alsa          alsactl       alsaloop      alsatplg      
alsabat       alsa_in       alsamixer     alsaucm       
alsabat-test  alsa-info     alsa_out      
dav@dav-ubu:~$ speaker-test -Dhw:2,0 -c 6 --rate 44100 --period 1024 -P 4

speaker-test 1.1.3

Playback device is hw:2,0
Stream parameters are 44100Hz, S16_LE, 6 channels
Using 16 octaves of pink noise
Rate set to 44100Hz (requested 44100Hz)
Buffer size range from 90 to 87381
Period size range from 45 to 43690
Requested period time 1024 us
Periods = 4
was set period_size = 45
was set buffer_size = 180
 0 - Front Left
 2 - Front Center
^C 1 - Front Right
 5 - Side Right
 4 - Side Left
 3 - LFE
Time per period = 3,258536
dav@dav-ubu:~$ speaker-test -Dhw:2,0 -c 6 --rate 44100 --period 1024 -P 4

speaker-test 1.1.3

Playback device is hw:2,0
Stream parameters are 44100Hz, S16_LE, 6 channels
Using 16 octaves of pink noise
Rate set to 44100Hz (requested 44100Hz)
Buffer size range from 90 to 87381
Period size range from 45 to 43690
Requested period time 1024 us
Periods = 4
was set period_size = 45
was set buffer_size = 180
 0 - Front Left
 2 - Front Center
^C 1 - Front Right
 5 - Side Right
 4 - Side Left
 3 - LFE
Time per period = 3,315164
dav@dav-ubu:~$ aplay -l
**** List of PLAYBACK Hardware Devices ****
card 0: PCH [HDA Intel PCH], device 0: ALC269VC Analog [ALC269VC Analog]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 1: NVidia [HDA NVidia], device 3: HDMI 0 [HDMI 0]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 1: NVidia [HDA NVidia], device 7: HDMI 1 [HDMI 1]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 1: NVidia [HDA NVidia], device 8: HDMI 2 [HDMI 2]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 2: E26 [Emagic EMI 2|6], device 0: USB Audio [USB Audio]
  Subdevices: 0/1
  Subdevice #0: subdevice #0
card 3: E26_1 [Emagic EMI 2|6], device 0: USB Audio [USB Audio]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
dav@dav-ubu:~$ alsa
alsa          alsactl       alsaloop      alsatplg      
alsabat       alsa_in       alsamixer     alsaucm       
alsabat-test  alsa-info     alsa_out      
dav@dav-ubu:~$ alsa_out 
Cannot connect to server socket err = No such file or directory
Cannot connect to server request channel
jackdmp 1.9.12
Copyright 2001-2005 Paul Davis and others.
Copyright 2004-2016 Grame.
Copyright 2016-2017 Filipe Coelho.
jackdmp comes with ABSOLUTELY NO WARRANTY
This is free software, and you are welcome to redistribute it
under certain conditions; see the file COPYING for details
no message buffer overruns
no message buffer overruns
no message buffer overruns
JACK server starting in realtime mode with priority 10
self-connect-mode is "Don't restrict self connect requests"
Jack: JackPosixThread::StartImp : create non RT thread
Jack: JackPosixThread::ThreadHandler : start
Jack: playback device hw:E26,0
Jack: capture device hw:E26,0
Jack: apparent rate = 44100
Jack: frames per period = 1024
Jack: JackDriver::Open capture_driver_name = hw:E26,0
Jack: JackDriver::Open playback_driver_name = hw:E26,0
Jack: Check protocol client = 8 server = 8
Jack: JackEngine::ClientInternalOpen: name = system
Jack: JackEngine::AllocateRefNum ref = 0
Jack: JackLinuxFutex::Allocate name = jack_sem.1000_default_system val = 0
Jack: JackEngine::NotifyAddClient: name = system
Jack: JackGraphManager::SetBufferSize size = 1024
Jack: JackConnectionManager::DirectConnect first: ref1 = 0 ref2 = 0
Jack: JackGraphManager::ConnectRefNum cur_index = 0 ref1 = 0 ref2 = 0
Jack: JackDriver::SetupDriverSync driver sem in flush mode
audio_reservation_init
Acquire audio card Audio2
creating alsa driver ... hw:E26,0|-|1024|4|44100|0|6|nomon|swmeter|-|16bit
configuring for 44100Hz, period = 1024 frames (23.2 ms), buffer = 4 periods
ALSA: final selected sample format for playback: 16bit little-endian
ALSA: use 4 periods for playback
Jack: JackSocketServerChannel::Open
Jack: JackServerSocket::Bind : addr.sun_path /dev/shm/jack_default_1000_0
Jack: JackSocketServerChannel::BuildPoolTable size = 1
Jack: JackEngine::Open
Jack: JackClientSocket::Connect : addr.sun_path /dev/shm/jack_default_1000_0
Jack: JackEngine::ClientInternalOpen: name = freewheel
Jack: JackEngine::AllocateRefNum ref = 1
Jack: JackLinuxFutex::Allocate name = jack_sem.1000_default_freewheel val = 0
Jack: JackEngine::NotifyAddClient: name = freewheel
Jack: JackDriver::ClientNotify ref = 1 driver = system name = freewheel notify = 0
Jack: JackDriver::ClientNotify ref = 0 driver = freewheel name = system notify = 0
Jack: JackConnectionManager::DirectConnect first: ref1 = 1 ref2 = 1
Jack: JackGraphManager::ConnectRefNum cur_index = 0 ref1 = 1 ref2 = 1
Jack: JackDriver::SetupDriverSync driver sem in flush mode
Jack: JackGraphManager::SetBufferSize size = 1024
Jack: JackAlsaDriver::Attach fBufferSize 1024 fSampleRate 44100
Jack: JackEngine::PortRegister ref = 0 name = system:playback_1 type = 32 bit float mono audio flags = 21 buffer_size = 1024
Jack: JackGraphManager::AllocatePortAux port_index = 1 name = system:playback_1 type = 32 bit float mono audio
Jack: JackConnectionManager::AddInputPort ref = 0 port = 1
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackAlsaDriver::Attach fPlaybackPortList[i] 1 
Jack: JackEngine::PortRegister ref = 0 name = system:playback_2 type = 32 bit float mono audio flags = 21 buffer_size = 1024
Jack: JackGraphManager::AllocatePortAux port_index = 2 name = system:playback_2 type = 32 bit float mono audio
Jack: JackConnectionManager::AddInputPort ref = 0 port = 2
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackAlsaDriver::Attach fPlaybackPortList[i] 2 
Jack: JackEngine::PortRegister ref = 0 name = system:playback_3 type = 32 bit float mono audio flags = 21 buffer_size = 1024
Jack: JackGraphManager::AllocatePortAux port_index = 3 name = system:playback_3 type = 32 bit float mono audio
Jack: JackConnectionManager::AddInputPort ref = 0 port = 3
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackAlsaDriver::Attach fPlaybackPortList[i] 3 
Jack: JackEngine::PortRegister ref = 0 name = system:playback_4 type = 32 bit float mono audio flags = 21 buffer_size = 1024
Jack: JackGraphManager::AllocatePortAux port_index = 4 name = system:playback_4 type = 32 bit float mono audio
Jack: JackConnectionManager::AddInputPort ref = 0 port = 4
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackAlsaDriver::Attach fPlaybackPortList[i] 4 
Jack: JackEngine::PortRegister ref = 0 name = system:playback_5 type = 32 bit float mono audio flags = 21 buffer_size = 1024
Jack: JackGraphManager::AllocatePortAux port_index = 5 name = system:playback_5 type = 32 bit float mono audio
Jack: JackConnectionManager::AddInputPort ref = 0 port = 5
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackAlsaDriver::Attach fPlaybackPortList[i] 5 
Jack: JackEngine::PortRegister ref = 0 name = system:playback_6 type = 32 bit float mono audio flags = 21 buffer_size = 1024
Jack: JackGraphManager::AllocatePortAux port_index = 6 name = system:playback_6 type = 32 bit float mono audio
Jack: JackConnectionManager::AddInputPort ref = 0 port = 6
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackAlsaDriver::Attach fPlaybackPortList[i] 6 
Jack: Clock source : system clock via clock_gettime
Jack: JackServer::Start
Jack: JackThreadedDriver::Start
Jack: JackPosixThread::StartImp : create non RT thread
Jack: JackPosixThread::ThreadHandler : start
Jack: JackThreadedDriver::Init real-time
Jack: JackPosixThread::AcquireRealTimeImp priority = 10
Jack: JackPosixThread::StartImp : create non RT thread
Jack: JackPosixThread::ThreadHandler : start
Jack: JackSocketServerChannel::ClientCreate socket
Jack: JackSocketServerChannel::BuildPoolTable size = 2
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 1 fd = 10
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackRequest::Notification
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackEngine::ClientNotify: no callback for notification = 4
Jack: JackEngine::ClientNotify: no callback for notification = 4
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackSocketServerChannel::ClientCreate socket
Jack: JackSocketServerChannel::BuildPoolTable size = 3
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 1 fd = 10
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 2 fd = 11
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackSocketServerChannel::Execute : fPollTable i = 2 fd = 11
Jack: JackSocketServerChannel::Execute : poll client error err = Success
Jack: JackSocketServerChannel::ClientKill ref = -1 fd = 11
Jack: Client was not opened : probably correspond to server_check
Jack: JackClientSocket::Close
Jack: JackSocketServerChannel::BuildPoolTable size = 2
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 1 fd = 10
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackSocketServerChannel::ClientCreate socket
Jack: JackSocketServerChannel::BuildPoolTable size = 3
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 1 fd = 10
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 2 fd = 11
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackSocketServerChannel::Execute : fPollTable i = 2 fd = 11
Jack: JackRequest::ClientCheck
Jack: Check protocol client = 8 server = 8
Jack: JackRequest::ClientOpen
Jack: JackEngine::ClientExternalOpen: uuid = 0, name = alsa_out
Jack: JackEngine::AllocateRefNum ref = 2
Jack: JackLinuxFutex::Allocate name = jack_sem.1000_default_alsa_out val = 0
Jack: JackSocketNotifyChannel::Open name = alsa_out
Jack: JackClientSocket::Connect : addr.sun_path /dev/shm/jack_alsa_out_1000_0
Jack: JackShmMem::new index = 2 attached = ec41f000 size = 422 
Jack: JackExternalClient::Open name = alsa_out index = 2 base = ec41f000
Jack: JackPosixProcessSync::TimedWait time out = 5000000
Jack: JackPosixProcessSync::TimedWait finished delta = 20115.0
Jack: JackEngine::NotifyAddClient: name = alsa_out
Jack: JackDriver::ClientNotify ref = 2 driver = system name = alsa_out notify = 0
Jack: JackExternalClient::ClientNotify ref = 0 client = alsa_out name = system notify = 0
Jack: JackDriver::ClientNotify ref = 2 driver = freewheel name = alsa_out notify = 0
Jack: JackExternalClient::ClientNotify ref = 1 client = alsa_out name = freewheel notify = 0
Jack: JackSocketServerChannel::ClientAdd ref = 2 fd = 11
Jack: JackSocketServerChannel::BuildPoolTable size = 3
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 1 fd = 10
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 2 fd = 11
Jack: JackClient::SetupDriverSync driver sem in flush mode
Jack: JackLinuxFutex::Connect name = jack_sem.1000_default_alsa_out
Jack: Clock source : system clock via clock_gettime
Jack: JackLibClient::Open name = alsa_out refnum = 2
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackRequest::Notification
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackEngine::ClientNotify: no callback for notification = 4
Jack: JackEngine::ClientNotify: no callback for notification = 4
Jack: JackEngine::ClientNotify: no callback for notification = 4
Jack: JackSocketServerChannel::Execute : fPollTable i = 2 fd = 11
selected sample format: 32bit
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackSocketServerChannel::Execute : fPollTable i = 2 fd = 11
Jack: JackRequest::RegisterPort
Jack: JackEngine::PortRegister ref = 2 name = alsa_out:playback_1 type = 32 bit float mono audio flags = 1 buffer_size = 0
Jack: JackGraphManager::AllocatePortAux port_index = 7 name = alsa_out:playback_1 type = 32 bit float mono audio
Jack: JackConnectionManager::AddInputPort ref = 2 port = 7
Jack: JackClient::PortRegister ref = 2 name = alsa_out:playback_1 type = 32 bit float mono audio port_index = 7
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackSocketServerChannel::Execute : fPollTable i = 2 fd = 11
Jack: JackRequest::RegisterPort
Jack: JackEngine::PortRegister ref = 2 name = alsa_out:playback_2 type = 32 bit float mono audio flags = 1 buffer_size = 0
Jack: JackGraphManager::AllocatePortAux port_index = 8 name = alsa_out:playback_2 type = 32 bit float mono audio
Jack: JackConnectionManager::AddInputPort ref = 2 port = 8
Jack: JackClient::PortRegister ref = 2 name = alsa_out:playback_2 type = 32 bit float mono audio port_index = 8
Jack: JackClient::Activate
Jack: JackPosixThread::StartImp : create non RT thread
Jack: JackPosixThread::ThreadHandler : start
Jack: JackClient::kBufferSizeCallback buffer_size = 1024
Jack: JackClient::Init : period = 23219 computation = 100 constraint = 23219
Jack: JackPosixThread::AcquireRealTimeImp priority = 5
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackSocketServerChannel::Execute : fPollTable i = 2 fd = 11
Jack: JackRequest::ActivateClient
Jack: JackEngine::ClientActivate ref = 2 name = alsa_out
Jack: JackConnectionManager::DirectConnect first: ref1 = 1 ref2 = 2
Jack: JackGraphManager::ConnectRefNum cur_index = 2 ref1 = 1 ref2 = 2
Jack: JackConnectionManager::DirectConnect first: ref1 = 2 ref2 = 1
Jack: JackGraphManager::ConnectRefNum cur_index = 2 ref1 = 2 ref2 = 1
Jack: JackPosixProcessSync::TimedWait time out = 464380
Jack: JackPosixProcessSync::TimedWait finished delta = 3461.0
Jack: JackExternalClient::ClientNotify ref = 2 client = alsa_out name = alsa_out notify = 2
Jack: JackClient::ClientNotify ref = 2 name = alsa_out notify = 2
Jack: JackClient::kActivateClient name = alsa_out ref = 2 
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackRequest::Notification
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackExternalClient::ClientNotify ref = 2 client = alsa_out name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 2 name = alsa_out notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackExternalClient::ClientNotify ref = 2 client = alsa_out name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 2 name = alsa_out notify = 18
Jack: WaitGraphChange...
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackEngine::ClientNotify: no callback for notification = 4
Jack: JackEngine::ClientNotify: no callback for notification = 4
Jack: JackEngine::ClientNotify: no callback for notification = 4
Jack: JackSocketServerChannel::Execute : fPollTable i = 2 fd = 11
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackRequest::Notification
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackExternalClient::ClientNotify ref = 2 client = alsa_out name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 2 name = alsa_out notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackExternalClient::ClientNotify ref = 2 client = alsa_out name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 2 name = alsa_out notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackEngine::ClientNotify: no callback for notification = 4
Jack: JackEngine::ClientNotify: no callback for notification = 4
Jack: JackEngine::ClientNotify: no callback for notification = 4
Jack: JackSocketServerChannel::Execute : fPollTable i = 2 fd = 11
^CJack: JackClient::Deactivate
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackSocketServerChannel::Execute : fPollTable i = 2 fd = 11
Jack: JackRequest::DeactivateClient
Jack: JackEngine::ClientDeactivate ref = 2 name = alsa_out
Jack: JackEngine::PortDisconnect ref = -1 src = 7 dst = 65535
Jack: JackEngine::PortDisconnect ref = -1 src = 8 dst = 65535
Jack: JackEngine::ClientNotify: no callback for notification = 10
Jack: JackEngine::ClientNotify: no callback for notification = 10
Jack: JackEngine::ClientNotify: no callback for notification = 10
Jack: JackEngine::ClientNotify: no callback for notification = 10
Jack: JackEngine::ClientNotify: no callback for notification = 10
Jack: JackEngine::ClientNotify: no callback for notification = 10
Jack: JackConnectionManager::DirectDisconnect last: ref1 = 2 ref2 = 1
Jack: JackGraphManager::DisconnectRefNum cur_index = 4 ref1 = 2 ref2 = 1
Jack: JackConnectionManager::DirectDisconnect last: ref1 = 1 ref2 = 2
Jack: JackGraphManager::DisconnectRefNum cur_index = 4 ref1 = 1 ref2 = 2
Jack: JackPosixProcessSync::TimedWait time out = 464380
Jack: JackPosixProcessSync::TimedWait finished delta = 11285.0
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackRequest::Notification
Jack: JackClient::Deactivate res = 0
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackPosixThread::Kill
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackEngine::ClientNotify: no callback for notification = 4
Jack: JackEngine::ClientNotify: no callback for notification = 4
Jack: JackEngine::ClientNotify: no callback for notification = 4
Jack: JackSocketServerChannel::Execute : fPollTable i = 2 fd = 11
Jack: jack_client_close
Jack: JackClient::Close ref = 2
Jack: JackClient::Deactivate
Jack: JackSocketClientChannel::Stop
Jack: JackPosixThread::Kill
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackSocketServerChannel::Execute : fPollTable i = 2 fd = 11
Jack: JackRequest::ClientClose
Jack: JackEngine::ClientExternalClose ref = 2
Jack: JackEngine::ClientCloseAux ref = 2
Jack: JackEngine::PortUnRegister ref = 2 port_index = 7
Jack: JackEngine::PortDisconnect ref = -1 src = 7 dst = 65535
Jack: JackGraphManager::DisconnectAllInput port_index = 7
Jack: JackConnectionManager::RemoveInputPort ref = 2 port_index = 7 
Jack: JackEngine::PortUnRegister ref = 2 port_index = 8
Jack: JackEngine::PortDisconnect ref = -1 src = 8 dst = 65535
Jack: JackGraphManager::DisconnectAllInput port_index = 8
Jack: JackConnectionManager::RemoveInputPort ref = 2 port_index = 8 
Jack: JackEngine::ReleaseRefnum server quit
JackTemporaryException : now quits...
Jack main caught signal 2
Jack: JackSocketServerChannel::ClientRemove ref = 2 fd = 11
Jack: JackClientSocket::Close
Jack: JackClientSocket::Close
Jack: JackServer::Stop
Jack: JackThreadedDriver::Stop
Jack: JackClientSocket::Close
Jack: JackPosixThread::Stop
Jack: JackSocketServerChannel::BuildPoolTable size = 2
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 1 fd = 10
Jack: JackLibClient::~JackLibClient
Jack: JackShmReadWritePtr1::~JackShmReadWritePtr1 2
Jack: Succeeded in unlocking 422 byte memory area
Jack: JackLibGlobals Destroy 9a68f310
Jack: ~JackLibGlobals
Jack: no message buffer overruns
Jack: JackPosixThread::Stop
Jack: JackPosixThread::ThreadHandler : exit
Jack: JackShmReadWritePtr::~JackShmReadWritePtr 1
Jack: Succeeded in unlocking 1187 byte memory area
Jack: JackShmReadWritePtr::~JackShmReadWritePtr 0
Jack: Succeeded in unlocking 82280346 byte memory area
Jack: jack_client_close res = 0
dav@dav-ubu:~$ Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackRequest::Notification
Jack: JackPosixThread::ThreadHandler : exit
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackEngine::ClientNotify: no callback for notification = 4
Jack: JackEngine::ClientNotify: no callback for notification = 4
Jack: JackPosixThread::Stop
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackRequest::Notification
Jack: JackRequest::Notification kQUIT
Jack: JackSocketServerChannel::Execute : JackQuitException
Jack: JackPosixThread::ThreadHandler : exit
Jack: JackEngine::ClientNotify: no callback for notification = 15
Jack: JackEngine::ClientNotify: no callback for notification = 15
Jack: JackServer::Close
Jack: JackServerSocket::Close /dev/shm/jack_default_1000_0
Jack: JackClientSocket::Close
Jack: JackAudioDriver::Detach
Jack: JackEngine::PortUnRegister ref = 0 port_index = 1
Jack: JackEngine::PortDisconnect ref = -1 src = 1 dst = 65535
Jack: JackGraphManager::DisconnectAllInput port_index = 1
Jack: JackConnectionManager::RemoveInputPort ref = 0 port_index = 1 
Jack: JackEngine::ClientNotify: no callback for notification = 10
Jack: JackEngine::ClientNotify: no callback for notification = 10
Jack: JackEngine::PortUnRegister ref = 0 port_index = 2
Jack: JackEngine::PortDisconnect ref = -1 src = 2 dst = 65535
Jack: JackGraphManager::DisconnectAllInput port_index = 2
Jack: JackConnectionManager::RemoveInputPort ref = 0 port_index = 2 
Jack: JackEngine::ClientNotify: no callback for notification = 10
Jack: JackEngine::ClientNotify: no callback for notification = 10
Jack: JackEngine::PortUnRegister ref = 0 port_index = 3
Jack: JackEngine::PortDisconnect ref = -1 src = 3 dst = 65535
Jack: JackGraphManager::DisconnectAllInput port_index = 3
Jack: JackConnectionManager::RemoveInputPort ref = 0 port_index = 3 
Jack: JackEngine::ClientNotify: no callback for notification = 10
Jack: JackEngine::ClientNotify: no callback for notification = 10
Jack: JackEngine::PortUnRegister ref = 0 port_index = 4
Jack: JackEngine::PortDisconnect ref = -1 src = 4 dst = 65535
Jack: JackGraphManager::DisconnectAllInput port_index = 4
Jack: JackConnectionManager::RemoveInputPort ref = 0 port_index = 4 
Jack: JackEngine::ClientNotify: no callback for notification = 10
Jack: JackEngine::ClientNotify: no callback for notification = 10
Jack: JackEngine::PortUnRegister ref = 0 port_index = 5
Jack: JackEngine::PortDisconnect ref = -1 src = 5 dst = 65535
Jack: JackGraphManager::DisconnectAllInput port_index = 5
Jack: JackConnectionManager::RemoveInputPort ref = 0 port_index = 5 
Jack: JackEngine::ClientNotify: no callback for notification = 10
Jack: JackEngine::ClientNotify: no callback for notification = 10
Jack: JackEngine::PortUnRegister ref = 0 port_index = 6
Jack: JackEngine::PortDisconnect ref = -1 src = 6 dst = 65535
Jack: JackGraphManager::DisconnectAllInput port_index = 6
Jack: JackConnectionManager::RemoveInputPort ref = 0 port_index = 6 
Jack: JackEngine::ClientNotify: no callback for notification = 10
Jack: JackEngine::ClientNotify: no callback for notification = 10
Jack: JackDriver::Close
Jack: JackConnectionManager::DirectDisconnect last: ref1 = 0 ref2 = 0
Jack: JackGraphManager::DisconnectRefNum cur_index = 6 ref1 = 0 ref2 = 0
Jack: JackEngine::ClientInternalClose ref = 0
Jack: JackEngine::ClientCloseAux ref = 0
Jack: JackGraphManager::RemoveAllPorts ref = 0
Jack: JackDriver::ClientNotify ref = 0 driver = freewheel name = system notify = 1
Released audio card Audio2
audio_reservation_finish
Jack: JackDriver::Close
Jack: JackConnectionManager::DirectDisconnect last: ref1 = 1 ref2 = 1
Jack: JackGraphManager::DisconnectRefNum cur_index = 6 ref1 = 1 ref2 = 1
Jack: JackEngine::ClientInternalClose ref = 1
Jack: JackEngine::ClientCloseAux ref = 1
Jack: JackGraphManager::RemoveAllPorts ref = 1
Jack: JackEngine::Close
Jack: JackClientSocket::Close
Jack: no message buffer overruns
Jack: JackPosixThread::Stop
Jack: JackPosixThread::ThreadHandler : exit
Jack: Succeeded in unlocking 82280346 byte memory area
Jack: JackShmMem::delete size = 0 index = 0
Jack: ~JackDriver
Jack: ~JackDriver
Jack: Succeeded in unlocking 1187 byte memory area
Jack: JackShmMem::delete size = 0 index = 1
Jack: Cleaning up shared memory
Jack: Cleaning up files
Jack: Unregistering server `default'
^C
dav@dav-ubu:~$ man alsa_out 
dav@dav-ubu:~$ alsa_out 
Jack: JackClient::SetupDriverSync driver sem in flush mode
Jack: JackLinuxFutex::Connect name = jack_sem.1000_default_alsa_out
Jack: Clock source : system clock via clock_gettime
Jack: JackLibClient::Open name = alsa_out refnum = 4
selected sample format: 32bit
Jack: JackClient::PortRegister ref = 4 name = alsa_out:playback_1 type = 32 bit float mono audio port_index = 7
Jack: JackClient::PortRegister ref = 4 name = alsa_out:playback_2 type = 32 bit float mono audio port_index = 8
Jack: JackClient::Activate
Jack: JackPosixThread::StartImp : create non RT thread
Jack: JackPosixThread::ThreadHandler : start
Jack: JackClient::kBufferSizeCallback buffer_size = 1024
Jack: JackClient::Init : period = 23219 computation = 100 constraint = 23219
Jack: JackPosixThread::AcquireRealTimeImp priority = 5
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 2
Jack: JackClient::kActivateClient name = alsa_out ref = 4 
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: WaitGraphChange...
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
^CJack: JackClient::Deactivate
Jack: JackClient::Deactivate res = 0
Jack: JackPosixThread::Kill
Jack: jack_client_close
Jack: JackClient::Close ref = 4
Jack: JackClient::Deactivate
Jack: JackSocketClientChannel::Stop
Jack: JackPosixThread::Kill
Jack: JackClientSocket::Close
Jack: JackClientSocket::Close
Jack: JackLibClient::~JackLibClient
Jack: JackShmReadWritePtr1::~JackShmReadWritePtr1 3
Jack: Succeeded in unlocking 422 byte memory area
Jack: JackLibGlobals Destroy 305c7310
Jack: ~JackLibGlobals
Jack: no message buffer overruns
Jack: JackPosixThread::Stop
Jack: JackPosixThread::ThreadHandler : exit
Jack: JackShmReadWritePtr::~JackShmReadWritePtr 1
Jack: Succeeded in unlocking 1187 byte memory area
Jack: JackShmReadWritePtr::~JackShmReadWritePtr 0
Jack: Succeeded in unlocking 82280346 byte memory area
Jack: jack_client_close res = 0
dav@dav-ubu:~$ alsa_out -D hw:3,0
alsa_out: invalid option -- 'D'
Unrecognized option: -D
usage: alsa_out [options]

  -j <jack name> - client name
  -d <alsa_device> 
  -c <channels> 
  -p <period_size> 
  -n <num_period> 
  -r <sample_rate> 
  -q <sample_rate quality [0..4]
  -m <max_diff> 
  -t <target_delay> 
  -i  turns on instrumentation
  -v  turns on printouts

dav@dav-ubu:~$ alsa_out -d hw:3,0
Jack: JackClient::SetupDriverSync driver sem in flush mode
Jack: JackLinuxFutex::Connect name = jack_sem.1000_default_alsa_out
Jack: Clock source : system clock via clock_gettime
Jack: JackLibClient::Open name = alsa_out refnum = 4
Rate doesn't match (requested 44100Hz, get 96000Hz)
Setting of hwparams failed: Invalid argument
dav@dav-ubu:~$ alsa_out -d hw:3,0 -c 6
Jack: JackClient::SetupDriverSync driver sem in flush mode
Jack: JackLinuxFutex::Connect name = jack_sem.1000_default_alsa_out
Jack: Clock source : system clock via clock_gettime
Jack: JackLibClient::Open name = alsa_out refnum = 4
Unable to set hw params for playback: Broken pipe
Setting of hwparams failed: Broken pipe
dav@dav-ubu:~$ speaker-test -Dhw:3,0 -c 6 --rate 44100 --period 1024 -P 4

speaker-test 1.1.3

Playback device is hw:3,0
Stream parameters are 44100Hz, S16_LE, 6 channels
Using 16 octaves of pink noise
Rate set to 44100Hz (requested 44100Hz)
Buffer size range from 90 to 87381
Period size range from 45 to 43690
Requested period time 1024 us
Periods = 4
was set period_size = 45
was set buffer_size = 180
 0 - Front Left
^C 2 - Front Center
 1 - Front Right
 5 - Side Right
 4 - Side Left
 3 - LFE
Time per period = 1,496118
dav@dav-ubu:~$ man alsa_out 
dav@dav-ubu:~$ alsa_out -d hw:3,0 -c 6 -n 4 -p 1024
Jack: JackClient::SetupDriverSync driver sem in flush mode
Jack: JackLinuxFutex::Connect name = jack_sem.1000_default_alsa_out
Jack: Clock source : system clock via clock_gettime
Jack: JackLibClient::Open name = alsa_out refnum = 4
Unable to set hw params for playback: Broken pipe
Setting of hwparams failed: Broken pipe
dav@dav-ubu:~$ ^C
dav@dav-ubu:~$ alsa_out -d hw:3,0 -c 6 -n 4 -p 1024 -r44100
Jack: JackClient::SetupDriverSync driver sem in flush mode
Jack: JackLinuxFutex::Connect name = jack_sem.1000_default_alsa_out
Jack: Clock source : system clock via clock_gettime
Jack: JackLibClient::Open name = alsa_out refnum = 4
Unable to set hw params for playback: Broken pipe
Setting of hwparams failed: Broken pipe
dav@dav-ubu:~$ man alsa_out 
dav@dav-ubu:~$ alsa_out -d hw:2,0 -c 6 -n 4 -p 1024 -r44100
Jack: JackClient::SetupDriverSync driver sem in flush mode
Jack: JackLinuxFutex::Connect name = jack_sem.1000_default_alsa_out
Jack: Clock source : system clock via clock_gettime
Jack: JackLibClient::Open name = alsa_out refnum = 4
Capture open error: Device or resource busy
dav@dav-ubu:~$ alsa_out -d hw:2,0 -c 6 -n 4 -p 1024 -r44100
Cannot connect to server socket err = No such file or directory
Cannot connect to server request channel
jackdmp 1.9.12
Copyright 2001-2005 Paul Davis and others.
Copyright 2004-2016 Grame.
Copyright 2016-2017 Filipe Coelho.
jackdmp comes with ABSOLUTELY NO WARRANTY
This is free software, and you are welcome to redistribute it
under certain conditions; see the file COPYING for details
no message buffer overruns
no message buffer overruns
no message buffer overruns
JACK server starting in realtime mode with priority 10
self-connect-mode is "Don't restrict self connect requests"
Jack: JackPosixThread::StartImp : create non RT thread
Jack: JackPosixThread::ThreadHandler : start
Jack: playback device hw:E26,0
Jack: capture device hw:E26,0
Jack: apparent rate = 44100
Jack: frames per period = 1024
Jack: JackDriver::Open capture_driver_name = hw:E26,0
Jack: JackDriver::Open playback_driver_name = hw:E26,0
Jack: Check protocol client = 8 server = 8
Jack: JackEngine::ClientInternalOpen: name = system
Jack: JackEngine::AllocateRefNum ref = 0
Jack: JackLinuxFutex::Allocate name = jack_sem.1000_default_system val = 0
Jack: JackEngine::NotifyAddClient: name = system
Jack: JackGraphManager::SetBufferSize size = 1024
Jack: JackConnectionManager::DirectConnect first: ref1 = 0 ref2 = 0
Jack: JackGraphManager::ConnectRefNum cur_index = 0 ref1 = 0 ref2 = 0
Jack: JackDriver::SetupDriverSync driver sem in flush mode
audio_reservation_init
Acquire audio card Audio2
creating alsa driver ... hw:E26,0|-|1024|4|44100|0|6|nomon|swmeter|-|16bit
configuring for 44100Hz, period = 1024 frames (23.2 ms), buffer = 4 periods
ALSA: final selected sample format for playback: 16bit little-endian
ALSA: use 4 periods for playback
Jack: JackSocketServerChannel::Open
Jack: JackServerSocket::Bind : addr.sun_path /dev/shm/jack_default_1000_0
Jack: JackSocketServerChannel::BuildPoolTable size = 1
Jack: JackEngine::Open
Jack: JackClientSocket::Connect : addr.sun_path /dev/shm/jack_default_1000_0
Jack: JackEngine::ClientInternalOpen: name = freewheel
Jack: JackEngine::AllocateRefNum ref = 1
Jack: JackLinuxFutex::Allocate name = jack_sem.1000_default_freewheel val = 0
Jack: JackEngine::NotifyAddClient: name = freewheel
Jack: JackDriver::ClientNotify ref = 1 driver = system name = freewheel notify = 0
Jack: JackDriver::ClientNotify ref = 0 driver = freewheel name = system notify = 0
Jack: JackConnectionManager::DirectConnect first: ref1 = 1 ref2 = 1
Jack: JackGraphManager::ConnectRefNum cur_index = 0 ref1 = 1 ref2 = 1
Jack: JackDriver::SetupDriverSync driver sem in flush mode
Jack: JackGraphManager::SetBufferSize size = 1024
Jack: JackAlsaDriver::Attach fBufferSize 1024 fSampleRate 44100
Jack: JackEngine::PortRegister ref = 0 name = system:playback_1 type = 32 bit float mono audio flags = 21 buffer_size = 1024
Jack: JackGraphManager::AllocatePortAux port_index = 1 name = system:playback_1 type = 32 bit float mono audio
Jack: JackConnectionManager::AddInputPort ref = 0 port = 1
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackAlsaDriver::Attach fPlaybackPortList[i] 1 
Jack: JackEngine::PortRegister ref = 0 name = system:playback_2 type = 32 bit float mono audio flags = 21 buffer_size = 1024
Jack: JackGraphManager::AllocatePortAux port_index = 2 name = system:playback_2 type = 32 bit float mono audio
Jack: JackConnectionManager::AddInputPort ref = 0 port = 2
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackAlsaDriver::Attach fPlaybackPortList[i] 2 
Jack: JackEngine::PortRegister ref = 0 name = system:playback_3 type = 32 bit float mono audio flags = 21 buffer_size = 1024
Jack: JackGraphManager::AllocatePortAux port_index = 3 name = system:playback_3 type = 32 bit float mono audio
Jack: JackConnectionManager::AddInputPort ref = 0 port = 3
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackAlsaDriver::Attach fPlaybackPortList[i] 3 
Jack: JackEngine::PortRegister ref = 0 name = system:playback_4 type = 32 bit float mono audio flags = 21 buffer_size = 1024
Jack: JackGraphManager::AllocatePortAux port_index = 4 name = system:playback_4 type = 32 bit float mono audio
Jack: JackConnectionManager::AddInputPort ref = 0 port = 4
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackAlsaDriver::Attach fPlaybackPortList[i] 4 
Jack: JackEngine::PortRegister ref = 0 name = system:playback_5 type = 32 bit float mono audio flags = 21 buffer_size = 1024
Jack: JackGraphManager::AllocatePortAux port_index = 5 name = system:playback_5 type = 32 bit float mono audio
Jack: JackConnectionManager::AddInputPort ref = 0 port = 5
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackAlsaDriver::Attach fPlaybackPortList[i] 5 
Jack: JackEngine::PortRegister ref = 0 name = system:playback_6 type = 32 bit float mono audio flags = 21 buffer_size = 1024
Jack: JackGraphManager::AllocatePortAux port_index = 6 name = system:playback_6 type = 32 bit float mono audio
Jack: JackConnectionManager::AddInputPort ref = 0 port = 6
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackAlsaDriver::Attach fPlaybackPortList[i] 6 
Jack: Clock source : system clock via clock_gettime
Jack: JackServer::Start
Jack: JackThreadedDriver::Start
Jack: JackPosixThread::StartImp : create non RT thread
Jack: JackPosixThread::ThreadHandler : start
Jack: JackThreadedDriver::Init real-time
Jack: JackPosixThread::AcquireRealTimeImp priority = 10
Jack: JackPosixThread::StartImp : create non RT thread
Jack: JackPosixThread::ThreadHandler : start
Jack: JackSocketServerChannel::ClientCreate socket
Jack: JackSocketServerChannel::BuildPoolTable size = 2
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 1 fd = 10
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackRequest::Notification
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackEngine::ClientNotify: no callback for notification = 4
Jack: JackEngine::ClientNotify: no callback for notification = 4
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackSocketServerChannel::ClientCreate socket
Jack: JackSocketServerChannel::BuildPoolTable size = 3
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 1 fd = 10
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 2 fd = 11
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackSocketServerChannel::Execute : fPollTable i = 2 fd = 11
Jack: JackSocketServerChannel::Execute : poll client error err = Success
Jack: JackSocketServerChannel::ClientKill ref = -1 fd = 11
Jack: Client was not opened : probably correspond to server_check
Jack: JackClientSocket::Close
Jack: JackSocketServerChannel::BuildPoolTable size = 2
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 1 fd = 10
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackSocketServerChannel::ClientCreate socket
Jack: JackSocketServerChannel::BuildPoolTable size = 3
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 1 fd = 10
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 2 fd = 11
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackSocketServerChannel::Execute : fPollTable i = 2 fd = 11
Jack: JackRequest::ClientCheck
Jack: Check protocol client = 8 server = 8
Jack: JackRequest::ClientOpen
Jack: JackEngine::ClientExternalOpen: uuid = 0, name = alsa_out
Jack: JackEngine::AllocateRefNum ref = 2
Jack: JackLinuxFutex::Allocate name = jack_sem.1000_default_alsa_out val = 0
Jack: JackSocketNotifyChannel::Open name = alsa_out
Jack: JackClientSocket::Connect : addr.sun_path /dev/shm/jack_alsa_out_1000_0
Jack: JackShmMem::new index = 2 attached = d66d0000 size = 422 
Jack: JackExternalClient::Open name = alsa_out index = 2 base = d66d0000
Jack: JackPosixProcessSync::TimedWait time out = 5000000
Jack: JackPosixProcessSync::TimedWait finished delta = 2131.0
Jack: JackEngine::NotifyAddClient: name = alsa_out
Jack: JackDriver::ClientNotify ref = 2 driver = system name = alsa_out notify = 0
Jack: JackExternalClient::ClientNotify ref = 0 client = alsa_out name = system notify = 0
Jack: JackDriver::ClientNotify ref = 2 driver = freewheel name = alsa_out notify = 0
Jack: JackExternalClient::ClientNotify ref = 1 client = alsa_out name = freewheel notify = 0
Jack: JackSocketServerChannel::ClientAdd ref = 2 fd = 11
Jack: JackSocketServerChannel::BuildPoolTable size = 3
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 1 fd = 10
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 2 fd = 11
Jack: JackClient::SetupDriverSync driver sem in flush mode
Jack: JackLinuxFutex::Connect name = jack_sem.1000_default_alsa_out
Jack: Clock source : system clock via clock_gettime
Jack: JackLibClient::Open name = alsa_out refnum = 2
Capture open error: Device or resource busy
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackRequest::Notification
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackEngine::ClientNotify: no callback for notification = 4
Jack: JackEngine::ClientNotify: no callback for notification = 4
Jack: JackEngine::ClientNotify: no callback for notification = 4
Jack: JackSocketServerChannel::Execute : fPollTable i = 2 fd = 11
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackSocketServerChannel::Execute : fPollTable i = 2 fd = 11
Jack: JackSocketServerChannel::Execute : poll client error err = Success
Jack: JackSocketServerChannel::ClientKill ref = 2 fd = 11
Jack: JackEngine::ClientKill ref = 2
Jack: JackEngine::ClientDeactivate ref = 2 name = alsa_out
dav@dav-ubu:~$ Jack: JackServer::Deactivate client = 2 was not activated
Jack: JackServer::Deactivate client = 2 was not activated
Jack: JackPosixProcessSync::TimedWait time out = 464380
Jack: JackPosixProcessSync::TimedWait finished delta = 13689.0
Jack: JackEngine::ClientExternalClose ref = 2
Jack: JackEngine::ClientCloseAux ref = 2
Jack: JackEngine::ReleaseRefnum server quit
Unknown error...
terminate called after throwing an instance of 'Jack::JackTemporaryException'
  what():  
^C
dav@dav-ubu:~$ alsa_out -d hw:3,0 -c 6 -n 4 -p 1024 -r44100
Cannot connect to server socket err = Connection refused
Cannot connect to server request channel
jackdmp 1.9.12
Copyright 2001-2005 Paul Davis and others.
Copyright 2004-2016 Grame.
Copyright 2016-2017 Filipe Coelho.
jackdmp comes with ABSOLUTELY NO WARRANTY
This is free software, and you are welcome to redistribute it
under certain conditions; see the file COPYING for details
no message buffer overruns
no message buffer overruns
no message buffer overruns
JACK server starting in realtime mode with priority 10
self-connect-mode is "Don't restrict self connect requests"
Jack: JackPosixThread::StartImp : create non RT thread
Jack: JackPosixThread::ThreadHandler : start
Jack: playback device hw:E26,0
Jack: capture device hw:E26,0
Jack: apparent rate = 44100
Jack: frames per period = 1024
Jack: JackDriver::Open capture_driver_name = hw:E26,0
Jack: JackDriver::Open playback_driver_name = hw:E26,0
Jack: Check protocol client = 8 server = 8
Jack: JackEngine::ClientInternalOpen: name = system
Jack: JackEngine::AllocateRefNum ref = 0
Jack: JackLinuxFutex::Allocate name = jack_sem.1000_default_system val = 0
Jack: JackEngine::NotifyAddClient: name = system
Jack: JackGraphManager::SetBufferSize size = 1024
Jack: JackConnectionManager::DirectConnect first: ref1 = 0 ref2 = 0
Jack: JackGraphManager::ConnectRefNum cur_index = 0 ref1 = 0 ref2 = 0
Jack: JackDriver::SetupDriverSync driver sem in flush mode
audio_reservation_init
Acquire audio card Audio2
creating alsa driver ... hw:E26,0|-|1024|4|44100|0|6|nomon|swmeter|-|16bit
configuring for 44100Hz, period = 1024 frames (23.2 ms), buffer = 4 periods
ALSA: final selected sample format for playback: 16bit little-endian
ALSA: use 4 periods for playback
Jack: JackSocketServerChannel::Open
Jack: JackServerSocket::Bind : addr.sun_path /dev/shm/jack_default_1000_0
Jack: JackSocketServerChannel::BuildPoolTable size = 1
Jack: JackEngine::Open
Jack: JackClientSocket::Connect : addr.sun_path /dev/shm/jack_default_1000_0
Jack: JackEngine::ClientInternalOpen: name = freewheel
Jack: JackEngine::AllocateRefNum ref = 1
Jack: JackLinuxFutex::Allocate name = jack_sem.1000_default_freewheel val = 0
Jack: JackEngine::NotifyAddClient: name = freewheel
Jack: JackDriver::ClientNotify ref = 1 driver = system name = freewheel notify = 0
Jack: JackDriver::ClientNotify ref = 0 driver = freewheel name = system notify = 0
Jack: JackConnectionManager::DirectConnect first: ref1 = 1 ref2 = 1
Jack: JackGraphManager::ConnectRefNum cur_index = 0 ref1 = 1 ref2 = 1
Jack: JackDriver::SetupDriverSync driver sem in flush mode
Jack: JackGraphManager::SetBufferSize size = 1024
Jack: JackAlsaDriver::Attach fBufferSize 1024 fSampleRate 44100
Jack: JackEngine::PortRegister ref = 0 name = system:playback_1 type = 32 bit float mono audio flags = 21 buffer_size = 1024
Jack: JackGraphManager::AllocatePortAux port_index = 1 name = system:playback_1 type = 32 bit float mono audio
Jack: JackConnectionManager::AddInputPort ref = 0 port = 1
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackAlsaDriver::Attach fPlaybackPortList[i] 1 
Jack: JackEngine::PortRegister ref = 0 name = system:playback_2 type = 32 bit float mono audio flags = 21 buffer_size = 1024
Jack: JackGraphManager::AllocatePortAux port_index = 2 name = system:playback_2 type = 32 bit float mono audio
Jack: JackConnectionManager::AddInputPort ref = 0 port = 2
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackAlsaDriver::Attach fPlaybackPortList[i] 2 
Jack: JackEngine::PortRegister ref = 0 name = system:playback_3 type = 32 bit float mono audio flags = 21 buffer_size = 1024
Jack: JackGraphManager::AllocatePortAux port_index = 3 name = system:playback_3 type = 32 bit float mono audio
Jack: JackConnectionManager::AddInputPort ref = 0 port = 3
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackAlsaDriver::Attach fPlaybackPortList[i] 3 
Jack: JackEngine::PortRegister ref = 0 name = system:playback_4 type = 32 bit float mono audio flags = 21 buffer_size = 1024
Jack: JackGraphManager::AllocatePortAux port_index = 4 name = system:playback_4 type = 32 bit float mono audio
Jack: JackConnectionManager::AddInputPort ref = 0 port = 4
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackAlsaDriver::Attach fPlaybackPortList[i] 4 
Jack: JackEngine::PortRegister ref = 0 name = system:playback_5 type = 32 bit float mono audio flags = 21 buffer_size = 1024
Jack: JackGraphManager::AllocatePortAux port_index = 5 name = system:playback_5 type = 32 bit float mono audio
Jack: JackConnectionManager::AddInputPort ref = 0 port = 5
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackAlsaDriver::Attach fPlaybackPortList[i] 5 
Jack: JackEngine::PortRegister ref = 0 name = system:playback_6 type = 32 bit float mono audio flags = 21 buffer_size = 1024
Jack: JackGraphManager::AllocatePortAux port_index = 6 name = system:playback_6 type = 32 bit float mono audio
Jack: JackConnectionManager::AddInputPort ref = 0 port = 6
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackAlsaDriver::Attach fPlaybackPortList[i] 6 
Jack: Clock source : system clock via clock_gettime
Jack: JackServer::Start
Jack: JackThreadedDriver::Start
Jack: JackPosixThread::StartImp : create non RT thread
Jack: JackPosixThread::ThreadHandler : start
Jack: JackThreadedDriver::Init real-time
Jack: JackPosixThread::AcquireRealTimeImp priority = 10
Jack: JackPosixThread::StartImp : create non RT thread
Jack: JackPosixThread::ThreadHandler : start
Jack: JackSocketServerChannel::ClientCreate socket
Jack: JackSocketServerChannel::BuildPoolTable size = 2
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 1 fd = 10
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackRequest::Notification
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackEngine::ClientNotify: no callback for notification = 4
Jack: JackEngine::ClientNotify: no callback for notification = 4
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackSocketServerChannel::ClientCreate socket
Jack: JackSocketServerChannel::BuildPoolTable size = 3
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 1 fd = 10
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 2 fd = 11
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackSocketServerChannel::Execute : fPollTable i = 2 fd = 11
Jack: JackSocketServerChannel::Execute : poll client error err = Success
Jack: JackSocketServerChannel::ClientKill ref = -1 fd = 11
Jack: Client was not opened : probably correspond to server_check
Jack: JackClientSocket::Close
Jack: JackSocketServerChannel::BuildPoolTable size = 2
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 1 fd = 10
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackSocketServerChannel::ClientCreate socket
Jack: JackSocketServerChannel::BuildPoolTable size = 3
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 1 fd = 10
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 2 fd = 11
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackSocketServerChannel::Execute : fPollTable i = 2 fd = 11
Jack: JackRequest::ClientCheck
Jack: Check protocol client = 8 server = 8
Jack: JackRequest::ClientOpen
Jack: JackEngine::ClientExternalOpen: uuid = 0, name = alsa_out
Jack: JackEngine::AllocateRefNum ref = 2
Jack: JackLinuxFutex::Allocate name = jack_sem.1000_default_alsa_out val = 0
Jack: JackSocketNotifyChannel::Open name = alsa_out
Jack: JackClientSocket::Connect : addr.sun_path /dev/shm/jack_alsa_out_1000_0
Jack: JackShmMem::new index = 2 attached = e43a1000 size = 422 
Jack: JackExternalClient::Open name = alsa_out index = 2 base = e43a1000
Jack: JackPosixProcessSync::TimedWait time out = 5000000
Jack: JackPosixProcessSync::TimedWait finished delta = 5018.0
Jack: JackEngine::NotifyAddClient: name = alsa_out
Jack: JackDriver::ClientNotify ref = 2 driver = system name = alsa_out notify = 0
Jack: JackExternalClient::ClientNotify ref = 0 client = alsa_out name = system notify = 0
Jack: JackDriver::ClientNotify ref = 2 driver = freewheel name = alsa_out notify = 0
Jack: JackExternalClient::ClientNotify ref = 1 client = alsa_out name = freewheel notify = 0
Jack: JackSocketServerChannel::ClientAdd ref = 2 fd = 11
Jack: JackSocketServerChannel::BuildPoolTable size = 3
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 1 fd = 10
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 2 fd = 11
Jack: JackClient::SetupDriverSync driver sem in flush mode
Jack: JackLinuxFutex::Connect name = jack_sem.1000_default_alsa_out
Jack: Clock source : system clock via clock_gettime
Jack: JackLibClient::Open name = alsa_out refnum = 2
Unable to set hw params for playback: Broken pipe
Setting of hwparams failed: Broken pipe
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackRequest::Notification
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackEngine::ClientNotify: no callback for notification = 4
Jack: JackEngine::ClientNotify: no callback for notification = 4
Jack: JackEngine::ClientNotify: no callback for notification = 4
Jack: JackSocketServerChannel::Execute : fPollTable i = 2 fd = 11
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackSocketServerChannel::Execute : fPollTable i = 2 fd = 11
Jack: JackSocketServerChannel::Execute : poll client error err = Success
Jack: JackSocketServerChannel::ClientKill ref = 2 fd = 11
Jack: JackEngine::ClientKill ref = 2
Jack: JackEngine::ClientDeactivate ref = 2 name = alsa_out
dav@dav-ubu:~$ Jack: JackServer::Deactivate client = 2 was not activated
Jack: JackServer::Deactivate client = 2 was not activated
Jack: JackPosixProcessSync::TimedWait time out = 464380
Jack: JackPosixProcessSync::TimedWait finished delta = 16800.0
Jack: JackEngine::ClientExternalClose ref = 2
Jack: JackEngine::ClientCloseAux ref = 2
Jack: JackEngine::ReleaseRefnum server quit
Unknown error...
terminate called after throwing an instance of 'Jack::JackTemporaryException'
  what():  
^C
dav@dav-ubu:~$ alsa_out -d hw:3,0 -c 6 -n 4 -p 1024 -r44100
Jack: JackClient::SetupDriverSync driver sem in flush mode
Jack: JackLinuxFutex::Connect name = jack_sem.1000_default_alsa_out
Jack: Clock source : system clock via clock_gettime
Jack: JackLibClient::Open name = alsa_out refnum = 4
Unable to set hw params for playback: Broken pipe
Setting of hwparams failed: Broken pipe
dav@dav-ubu:~$ alsa_out -d hw:3,0 -c 6 -n 4 -p 1024 -r44100 --help
alsa_out: invalid option -- '-'
Unrecognized option: --
alsa_out: invalid option -- 'h'
Unrecognized option: -h
alsa_out: invalid option -- 'e'
Unrecognized option: -e
alsa_out: invalid option -- 'l'
Unrecognized option: -l
alsa_out: option requires an argument -- 'p'
Unrecognized option: -p
usage: alsa_out [options]

  -j <jack name> - client name
  -d <alsa_device> 
  -c <channels> 
  -p <period_size> 
  -n <num_period> 
  -r <sample_rate> 
  -q <sample_rate quality [0..4]
  -m <max_diff> 
  -t <target_delay> 
  -i  turns on instrumentation
  -v  turns on printouts

dav@dav-ubu:~$ alsa_out -d hw:3,0 -c 6 -n 4 -p 1024 -r44100 -v
Jack: JackClient::SetupDriverSync driver sem in flush mode
Jack: JackLinuxFutex::Connect name = jack_sem.1000_default_alsa_out
Jack: Clock source : system clock via clock_gettime
Jack: JackLibClient::Open name = alsa_out refnum = 4
Unable to set hw params for playback: Broken pipe
Setting of hwparams failed: Broken pipe
dav@dav-ubu:~$ alsa_out -d hw:3,0 -c 6 -n 4 -p 1024 -r44100 -vi
Jack: JackClient::SetupDriverSync driver sem in flush mode
Jack: JackLinuxFutex::Connect name = jack_sem.1000_default_alsa_out
Jack: Clock source : system clock via clock_gettime
Jack: JackLibClient::Open name = alsa_out refnum = 4
Unable to set hw params for playback: Broken pipe
Setting of hwparams failed: Broken pipe
dav@dav-ubu:~$ alsa_out -d hw:3,0 -c 6 -n 4 -p 1024 -r44100 -v -i
Jack: JackClient::SetupDriverSync driver sem in flush mode
Jack: JackLinuxFutex::Connect name = jack_sem.1000_default_alsa_out
Jack: Clock source : system clock via clock_gettime
Jack: JackLibClient::Open name = alsa_out refnum = 4
Unable to set hw params for playback: Broken pipe
Setting of hwparams failed: Broken pipe
dav@dav-ubu:~$ alsa_out -d hw:3,0 -c 6 -n 4 -p 1024 -r44100 -v -i
Cannot connect to server socket err = No such file or directory
Cannot connect to server request channel
jackdmp 1.9.12
Copyright 2001-2005 Paul Davis and others.
Copyright 2004-2016 Grame.
Copyright 2016-2017 Filipe Coelho.
jackdmp comes with ABSOLUTELY NO WARRANTY
This is free software, and you are welcome to redistribute it
under certain conditions; see the file COPYING for details
no message buffer overruns
no message buffer overruns
no message buffer overruns
JACK server starting in realtime mode with priority 10
self-connect-mode is "Don't restrict self connect requests"
Jack: JackPosixThread::StartImp : create non RT thread
Jack: JackPosixThread::ThreadHandler : start
Jack: playback device hw:E26,0
Jack: capture device hw:E26,0
Jack: apparent rate = 44100
Jack: frames per period = 1024
Jack: JackDriver::Open capture_driver_name = hw:E26,0
Jack: JackDriver::Open playback_driver_name = hw:E26,0
Jack: Check protocol client = 8 server = 8
Jack: JackEngine::ClientInternalOpen: name = system
Jack: JackEngine::AllocateRefNum ref = 0
Jack: JackLinuxFutex::Allocate name = jack_sem.1000_default_system val = 0
Jack: JackEngine::NotifyAddClient: name = system
Jack: JackGraphManager::SetBufferSize size = 1024
Jack: JackConnectionManager::DirectConnect first: ref1 = 0 ref2 = 0
Jack: JackGraphManager::ConnectRefNum cur_index = 0 ref1 = 0 ref2 = 0
Jack: JackDriver::SetupDriverSync driver sem in flush mode
audio_reservation_init
Acquire audio card Audio2
creating alsa driver ... hw:E26,0|-|1024|4|44100|0|6|nomon|swmeter|-|16bit
configuring for 44100Hz, period = 1024 frames (23.2 ms), buffer = 4 periods
ALSA: final selected sample format for playback: 16bit little-endian
ALSA: use 4 periods for playback
Jack: JackSocketServerChannel::Open
Jack: JackServerSocket::Bind : addr.sun_path /dev/shm/jack_default_1000_0
Jack: JackSocketServerChannel::BuildPoolTable size = 1
Jack: JackEngine::Open
Jack: JackClientSocket::Connect : addr.sun_path /dev/shm/jack_default_1000_0
Jack: JackEngine::ClientInternalOpen: name = freewheel
Jack: JackEngine::AllocateRefNum ref = 1
Jack: JackLinuxFutex::Allocate name = jack_sem.1000_default_freewheel val = 0
Jack: JackEngine::NotifyAddClient: name = freewheel
Jack: JackDriver::ClientNotify ref = 1 driver = system name = freewheel notify = 0
Jack: JackDriver::ClientNotify ref = 0 driver = freewheel name = system notify = 0
Jack: JackConnectionManager::DirectConnect first: ref1 = 1 ref2 = 1
Jack: JackGraphManager::ConnectRefNum cur_index = 0 ref1 = 1 ref2 = 1
Jack: JackDriver::SetupDriverSync driver sem in flush mode
Jack: JackGraphManager::SetBufferSize size = 1024
Jack: JackAlsaDriver::Attach fBufferSize 1024 fSampleRate 44100
Jack: JackEngine::PortRegister ref = 0 name = system:playback_1 type = 32 bit float mono audio flags = 21 buffer_size = 1024
Jack: JackGraphManager::AllocatePortAux port_index = 1 name = system:playback_1 type = 32 bit float mono audio
Jack: JackConnectionManager::AddInputPort ref = 0 port = 1
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackAlsaDriver::Attach fPlaybackPortList[i] 1 
Jack: JackEngine::PortRegister ref = 0 name = system:playback_2 type = 32 bit float mono audio flags = 21 buffer_size = 1024
Jack: JackGraphManager::AllocatePortAux port_index = 2 name = system:playback_2 type = 32 bit float mono audio
Jack: JackConnectionManager::AddInputPort ref = 0 port = 2
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackAlsaDriver::Attach fPlaybackPortList[i] 2 
Jack: JackEngine::PortRegister ref = 0 name = system:playback_3 type = 32 bit float mono audio flags = 21 buffer_size = 1024
Jack: JackGraphManager::AllocatePortAux port_index = 3 name = system:playback_3 type = 32 bit float mono audio
Jack: JackConnectionManager::AddInputPort ref = 0 port = 3
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackAlsaDriver::Attach fPlaybackPortList[i] 3 
Jack: JackEngine::PortRegister ref = 0 name = system:playback_4 type = 32 bit float mono audio flags = 21 buffer_size = 1024
Jack: JackGraphManager::AllocatePortAux port_index = 4 name = system:playback_4 type = 32 bit float mono audio
Jack: JackConnectionManager::AddInputPort ref = 0 port = 4
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackAlsaDriver::Attach fPlaybackPortList[i] 4 
Jack: JackEngine::PortRegister ref = 0 name = system:playback_5 type = 32 bit float mono audio flags = 21 buffer_size = 1024
Jack: JackGraphManager::AllocatePortAux port_index = 5 name = system:playback_5 type = 32 bit float mono audio
Jack: JackConnectionManager::AddInputPort ref = 0 port = 5
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackAlsaDriver::Attach fPlaybackPortList[i] 5 
Jack: JackEngine::PortRegister ref = 0 name = system:playback_6 type = 32 bit float mono audio flags = 21 buffer_size = 1024
Jack: JackGraphManager::AllocatePortAux port_index = 6 name = system:playback_6 type = 32 bit float mono audio
Jack: JackConnectionManager::AddInputPort ref = 0 port = 6
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackEngine::ClientNotify: no callback for notification = 9
Jack: JackAlsaDriver::Attach fPlaybackPortList[i] 6 
Jack: Clock source : system clock via clock_gettime
Jack: JackServer::Start
Jack: JackThreadedDriver::Start
Jack: JackPosixThread::StartImp : create non RT thread
Jack: JackPosixThread::ThreadHandler : start
Jack: JackThreadedDriver::Init real-time
Jack: JackPosixThread::AcquireRealTimeImp priority = 10
Jack: JackPosixThread::StartImp : create non RT thread
Jack: JackPosixThread::ThreadHandler : start
Jack: JackSocketServerChannel::ClientCreate socket
Jack: JackSocketServerChannel::BuildPoolTable size = 2
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 1 fd = 10
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackRequest::Notification
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackEngine::ClientNotify: no callback for notification = 4
Jack: JackEngine::ClientNotify: no callback for notification = 4
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackSocketServerChannel::ClientCreate socket
Jack: JackSocketServerChannel::BuildPoolTable size = 3
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 1 fd = 10
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 2 fd = 11
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackSocketServerChannel::Execute : fPollTable i = 2 fd = 11
Jack: JackSocketServerChannel::Execute : poll client error err = Success
Jack: JackSocketServerChannel::ClientKill ref = -1 fd = 11
Jack: Client was not opened : probably correspond to server_check
Jack: JackClientSocket::Close
Jack: JackSocketServerChannel::BuildPoolTable size = 2
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 1 fd = 10
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackSocketServerChannel::ClientCreate socket
Jack: JackSocketServerChannel::BuildPoolTable size = 3
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 1 fd = 10
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 2 fd = 11
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackSocketServerChannel::Execute : fPollTable i = 2 fd = 11
Jack: JackRequest::ClientCheck
Jack: Check protocol client = 8 server = 8
Jack: JackRequest::ClientOpen
Jack: JackEngine::ClientExternalOpen: uuid = 0, name = alsa_out
Jack: JackEngine::AllocateRefNum ref = 2
Jack: JackLinuxFutex::Allocate name = jack_sem.1000_default_alsa_out val = 0
Jack: JackSocketNotifyChannel::Open name = alsa_out
Jack: JackClientSocket::Connect : addr.sun_path /dev/shm/jack_alsa_out_1000_0
Jack: JackShmMem::new index = 2 attached = 682a000 size = 422 
Jack: JackExternalClient::Open name = alsa_out index = 2 base = 682a000
Jack: JackPosixProcessSync::TimedWait time out = 5000000
Jack: JackPosixProcessSync::TimedWait finished delta = 10337.0
Jack: JackEngine::NotifyAddClient: name = alsa_out
Jack: JackDriver::ClientNotify ref = 2 driver = system name = alsa_out notify = 0
Jack: JackExternalClient::ClientNotify ref = 0 client = alsa_out name = system notify = 0
Jack: JackDriver::ClientNotify ref = 2 driver = freewheel name = alsa_out notify = 0
Jack: JackExternalClient::ClientNotify ref = 1 client = alsa_out name = freewheel notify = 0
Jack: JackSocketServerChannel::ClientAdd ref = 2 fd = 11
Jack: JackSocketServerChannel::BuildPoolTable size = 3
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 1 fd = 10
Jack: JackSocketServerChannel::BuildPoolTable fSocketTable i = 2 fd = 11
Jack: JackClient::SetupDriverSync driver sem in flush mode
Jack: JackLinuxFutex::Connect name = jack_sem.1000_default_alsa_out
Jack: Clock source : system clock via clock_gettime
Jack: JackLibClient::Open name = alsa_out refnum = 2
Unable to set hw params for playback: Broken pipe
Setting of hwparams failed: Broken pipe
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackRequest::Notification
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackDriver::ClientNotify ref = 1 driver = freewheel name = freewheel notify = 18
Jack: JackEngine::ClientNotify: no callback for notification = 4
Jack: JackEngine::ClientNotify: no callback for notification = 4
Jack: JackEngine::ClientNotify: no callback for notification = 4
Jack: JackSocketServerChannel::Execute : fPollTable i = 2 fd = 11
Jack: JackSocketServerChannel::Execute : fPollTable i = 1 fd = 10
Jack: JackSocketServerChannel::Execute : fPollTable i = 2 fd = 11
Jack: JackSocketServerChannel::Execute : poll client error err = Success
Jack: JackSocketServerChannel::ClientKill ref = 2 fd = 11
Jack: JackEngine::ClientKill ref = 2
Jack: JackEngine::ClientDeactivate ref = 2 name = alsa_out
dav@dav-ubu:~$ Jack: JackServer::Deactivate client = 2 was not activated
Jack: JackServer::Deactivate client = 2 was not activated
Jack: JackPosixProcessSync::TimedWait time out = 464380
Jack: JackPosixProcessSync::TimedWait finished delta = 3542.0
Jack: JackEngine::ClientExternalClose ref = 2
Jack: JackEngine::ClientCloseAux ref = 2
Jack: JackEngine::ReleaseRefnum server quit
Unknown error...
terminate called after throwing an instance of 'Jack::JackTemporaryException'
  what():  
^C
dav@dav-ubu:~$ alsa_out -d hw:3,0 -c 6 -n 4 -p 1024 -r44100 -v -i
Jack: JackClient::SetupDriverSync driver sem in flush mode
Jack: JackLinuxFutex::Connect name = jack_sem.1000_default_alsa_out
Jack: Clock source : system clock via clock_gettime
Jack: JackLibClient::Open name = alsa_out refnum = 4
Unable to set hw params for playback: Broken pipe
Setting of hwparams failed: Broken pipe
dav@dav-ubu:~$ ^C
dav@dav-ubu:~$ gedit .asoundrc
dav@dav-ubu:~$ aplay -l
**** List of PLAYBACK Hardware Devices ****
card 0: PCH [HDA Intel PCH], device 0: ALC269VC Analog [ALC269VC Analog]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 1: NVidia [HDA NVidia], device 3: HDMI 0 [HDMI 0]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 1: NVidia [HDA NVidia], device 7: HDMI 1 [HDMI 1]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 1: NVidia [HDA NVidia], device 8: HDMI 2 [HDMI 2]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 2: E26 [Emagic EMI 2|6], device 0: USB Audio [USB Audio]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 3: E26_1 [Emagic EMI 2|6], device 0: USB Audio [USB Audio]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
dav@dav-ubu:~$ aplay -L
null
    Discard all samples (playback) or generate zero samples (capture)
pulse
    PulseAudio Sound Server
merge
default:CARD=PCH
    HDA Intel PCH, ALC269VC Analog
    Default Audio Device
sysdefault:CARD=PCH
    HDA Intel PCH, ALC269VC Analog
    Default Audio Device
front:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    Front speakers
surround21:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    2.1 Surround output to Front and Subwoofer speakers
surround40:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    4.0 Surround output to Front and Rear speakers
surround41:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    4.1 Surround output to Front, Rear and Subwoofer speakers
surround50:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    5.0 Surround output to Front, Center and Rear speakers
surround51:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    5.1 Surround output to Front, Center, Rear and Subwoofer speakers
surround71:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    7.1 Surround output to Front, Center, Side, Rear and Woofer speakers
dmix:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    Direct sample mixing device
dsnoop:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    Direct sample snooping device
hw:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    Direct hardware device without any conversions
plughw:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    Hardware device with all software conversions
hdmi:CARD=NVidia,DEV=0
    HDA NVidia, HDMI 0
    HDMI Audio Output
hdmi:CARD=NVidia,DEV=1
    HDA NVidia, HDMI 1
    HDMI Audio Output
hdmi:CARD=NVidia,DEV=2
    HDA NVidia, HDMI 2
    HDMI Audio Output
dmix:CARD=NVidia,DEV=3
    HDA NVidia, HDMI 0
    Direct sample mixing device
dmix:CARD=NVidia,DEV=7
    HDA NVidia, HDMI 1
    Direct sample mixing device
dmix:CARD=NVidia,DEV=8
    HDA NVidia, HDMI 2
    Direct sample mixing device
dsnoop:CARD=NVidia,DEV=3
    HDA NVidia, HDMI 0
    Direct sample snooping device
dsnoop:CARD=NVidia,DEV=7
    HDA NVidia, HDMI 1
    Direct sample snooping device
dsnoop:CARD=NVidia,DEV=8
    HDA NVidia, HDMI 2
    Direct sample snooping device
hw:CARD=NVidia,DEV=3
    HDA NVidia, HDMI 0
    Direct hardware device without any conversions
hw:CARD=NVidia,DEV=7
    HDA NVidia, HDMI 1
    Direct hardware device without any conversions
hw:CARD=NVidia,DEV=8
    HDA NVidia, HDMI 2
    Direct hardware device without any conversions
plughw:CARD=NVidia,DEV=3
    HDA NVidia, HDMI 0
    Hardware device with all software conversions
plughw:CARD=NVidia,DEV=7
    HDA NVidia, HDMI 1
    Hardware device with all software conversions
plughw:CARD=NVidia,DEV=8
    HDA NVidia, HDMI 2
    Hardware device with all software conversions
default:CARD=E26
    Emagic EMI 2
    Default Audio Device
sysdefault:CARD=E26
    Emagic EMI 2
    Default Audio Device
front:CARD=E26,DEV=0
    Emagic EMI 2
    Front speakers
surround21:CARD=E26,DEV=0
    Emagic EMI 2
    2.1 Surround output to Front and Subwoofer speakers
surround40:CARD=E26,DEV=0
    Emagic EMI 2
    4.0 Surround output to Front and Rear speakers
surround41:CARD=E26,DEV=0
    Emagic EMI 2
    4.1 Surround output to Front, Rear and Subwoofer speakers
surround50:CARD=E26,DEV=0
    Emagic EMI 2
    5.0 Surround output to Front, Center and Rear speakers
surround51:CARD=E26,DEV=0
    Emagic EMI 2
    5.1 Surround output to Front, Center, Rear and Subwoofer speakers
surround71:CARD=E26,DEV=0
    Emagic EMI 2
    7.1 Surround output to Front, Center, Side, Rear and Woofer speakers
iec958:CARD=E26,DEV=0
    Emagic EMI 2
    IEC958 (S/PDIF) Digital Audio Output
dmix:CARD=E26,DEV=0
    Emagic EMI 2
    Direct sample mixing device
dsnoop:CARD=E26,DEV=0
    Emagic EMI 2
    Direct sample snooping device
hw:CARD=E26,DEV=0
    Emagic EMI 2
    Direct hardware device without any conversions
plughw:CARD=E26,DEV=0
    Emagic EMI 2
    Hardware device with all software conversions
default:CARD=E26_1
    Emagic EMI 2
    Default Audio Device
sysdefault:CARD=E26_1
    Emagic EMI 2
    Default Audio Device
front:CARD=E26_1,DEV=0
    Emagic EMI 2
    Front speakers
surround21:CARD=E26_1,DEV=0
    Emagic EMI 2
    2.1 Surround output to Front and Subwoofer speakers
surround40:CARD=E26_1,DEV=0
    Emagic EMI 2
    4.0 Surround output to Front and Rear speakers
surround41:CARD=E26_1,DEV=0
    Emagic EMI 2
    4.1 Surround output to Front, Rear and Subwoofer speakers
surround50:CARD=E26_1,DEV=0
    Emagic EMI 2
    5.0 Surround output to Front, Center and Rear speakers
surround51:CARD=E26_1,DEV=0
    Emagic EMI 2
    5.1 Surround output to Front, Center, Rear and Subwoofer speakers
surround71:CARD=E26_1,DEV=0
    Emagic EMI 2
    7.1 Surround output to Front, Center, Side, Rear and Woofer speakers
iec958:CARD=E26_1,DEV=0
    Emagic EMI 2
    IEC958 (S/PDIF) Digital Audio Output
dmix:CARD=E26_1,DEV=0
    Emagic EMI 2
    Direct sample mixing device
dsnoop:CARD=E26_1,DEV=0
    Emagic EMI 2
    Direct sample snooping device
hw:CARD=E26_1,DEV=0
    Emagic EMI 2
    Direct hardware device without any conversions
plughw:CARD=E26_1,DEV=0
    Emagic EMI 2
    Hardware device with all software conversions
dav@dav-ubu:~$ speaker-test -Dplug:merge -c 12

speaker-test 1.1.3

Playback device is plug:merge
Stream parameters are 48000Hz, S16_LE, 12 channels
Using 16 octaves of pink noise
speaker-test: pcm_multi.c:1062: snd_pcm_multi_open: Assertion `!slave_map[sidxs[i]][schannels[i]]' failed.
dav@dav-ubu:~$ speaker-test -Dplug:merge -c 12

speaker-test 1.1.3

Playback device is plug:merge
Stream parameters are 48000Hz, S16_LE, 12 channels
Using 16 octaves of pink noise
speaker-test: pcm_multi.c:1062: snd_pcm_multi_open: Assertion `!slave_map[sidxs[i]][schannels[i]]' failed.
dav@dav-ubu:~$ speaker-test -Dplug:merge -c 6 --rate 44100 --period 1024 -P 4

speaker-test 1.1.3

Playback device is plug:merge
Stream parameters are 44100Hz, S16_LE, 6 channels
Using 16 octaves of pink noise
speaker-test: pcm_multi.c:1062: snd_pcm_multi_open: Assertion `!slave_map[sidxs[i]][schannels[i]]' failed.
dav@dav-ubu:~$ speaker-test -Dplug:merge -c 12 --rate 44100 --period 1024 -P 4

speaker-test 1.1.3

Playback device is plug:merge
Stream parameters are 44100Hz, S16_LE, 12 channels
Using 16 octaves of pink noise
speaker-test: pcm_multi.c:1062: snd_pcm_multi_open: Assertion `!slave_map[sidxs[i]][schannels[i]]' failed.
dav@dav-ubu:~$ speaker-test -Dplug:merge -c 12 --rate 44100 --period 1024 -P 4

speaker-test 1.1.3

Playback device is plug:merge
Stream parameters are 44100Hz, S16_LE, 12 channels
Using 16 octaves of pink noise
Rate set to 44100Hz (requested 44100Hz)
Buffer size range from 90 to 87381
Period size range from 45 to 43690
Requested period time 1024 us
Periods = 4
was set period_size = 45
was set buffer_size = 180
 0 - Front Left
 6 - Front Left
 2 - Front Center
 8 - Front Center
^C^C^[[A^C^C^C^[[A^C^C^C^C^C^TKilled
dav@dav-ubu:~$ aplay -L
null
    Discard all samples (playback) or generate zero samples (capture)
pulse
    PulseAudio Sound Server
merge
default:CARD=PCH
    HDA Intel PCH, ALC269VC Analog
    Default Audio Device
sysdefault:CARD=PCH
    HDA Intel PCH, ALC269VC Analog
    Default Audio Device
front:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    Front speakers
surround21:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    2.1 Surround output to Front and Subwoofer speakers
surround40:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    4.0 Surround output to Front and Rear speakers
surround41:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    4.1 Surround output to Front, Rear and Subwoofer speakers
surround50:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    5.0 Surround output to Front, Center and Rear speakers
surround51:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    5.1 Surround output to Front, Center, Rear and Subwoofer speakers
surround71:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    7.1 Surround output to Front, Center, Side, Rear and Woofer speakers
dmix:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    Direct sample mixing device
dsnoop:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    Direct sample snooping device
hw:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    Direct hardware device without any conversions
plughw:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    Hardware device with all software conversions
hdmi:CARD=NVidia,DEV=0
    HDA NVidia, HDMI 0
    HDMI Audio Output
hdmi:CARD=NVidia,DEV=1
    HDA NVidia, HDMI 1
    HDMI Audio Output
hdmi:CARD=NVidia,DEV=2
    HDA NVidia, HDMI 2
    HDMI Audio Output
dmix:CARD=NVidia,DEV=3
    HDA NVidia, HDMI 0
    Direct sample mixing device
dmix:CARD=NVidia,DEV=7
    HDA NVidia, HDMI 1
    Direct sample mixing device
dmix:CARD=NVidia,DEV=8
    HDA NVidia, HDMI 2
    Direct sample mixing device
dsnoop:CARD=NVidia,DEV=3
    HDA NVidia, HDMI 0
    Direct sample snooping device
dsnoop:CARD=NVidia,DEV=7
    HDA NVidia, HDMI 1
    Direct sample snooping device
dsnoop:CARD=NVidia,DEV=8
    HDA NVidia, HDMI 2
    Direct sample snooping device
hw:CARD=NVidia,DEV=3
    HDA NVidia, HDMI 0
    Direct hardware device without any conversions
hw:CARD=NVidia,DEV=7
    HDA NVidia, HDMI 1
    Direct hardware device without any conversions
hw:CARD=NVidia,DEV=8
    HDA NVidia, HDMI 2
    Direct hardware device without any conversions
plughw:CARD=NVidia,DEV=3
    HDA NVidia, HDMI 0
    Hardware device with all software conversions
plughw:CARD=NVidia,DEV=7
    HDA NVidia, HDMI 1
    Hardware device with all software conversions
plughw:CARD=NVidia,DEV=8
    HDA NVidia, HDMI 2
    Hardware device with all software conversions
default:CARD=E26
    Emagic EMI 2
    Default Audio Device
sysdefault:CARD=E26
    Emagic EMI 2
    Default Audio Device
front:CARD=E26,DEV=0
    Emagic EMI 2
    Front speakers
surround21:CARD=E26,DEV=0
    Emagic EMI 2
    2.1 Surround output to Front and Subwoofer speakers
surround40:CARD=E26,DEV=0
    Emagic EMI 2
    4.0 Surround output to Front and Rear speakers
surround41:CARD=E26,DEV=0
    Emagic EMI 2
    4.1 Surround output to Front, Rear and Subwoofer speakers
surround50:CARD=E26,DEV=0
    Emagic EMI 2
    5.0 Surround output to Front, Center and Rear speakers
surround51:CARD=E26,DEV=0
    Emagic EMI 2
    5.1 Surround output to Front, Center, Rear and Subwoofer speakers
surround71:CARD=E26,DEV=0
    Emagic EMI 2
    7.1 Surround output to Front, Center, Side, Rear and Woofer speakers
iec958:CARD=E26,DEV=0
    Emagic EMI 2
    IEC958 (S/PDIF) Digital Audio Output
dmix:CARD=E26,DEV=0
    Emagic EMI 2
    Direct sample mixing device
dsnoop:CARD=E26,DEV=0
    Emagic EMI 2
    Direct sample snooping device
hw:CARD=E26,DEV=0
    Emagic EMI 2
    Direct hardware device without any conversions
plughw:CARD=E26,DEV=0
    Emagic EMI 2
    Hardware device with all software conversions
default:CARD=E26_1
    Emagic EMI 2
    Default Audio Device
sysdefault:CARD=E26_1
    Emagic EMI 2
    Default Audio Device
front:CARD=E26_1,DEV=0
    Emagic EMI 2
    Front speakers
surround21:CARD=E26_1,DEV=0
    Emagic EMI 2
    2.1 Surround output to Front and Subwoofer speakers
surround40:CARD=E26_1,DEV=0
    Emagic EMI 2
    4.0 Surround output to Front and Rear speakers
surround41:CARD=E26_1,DEV=0
    Emagic EMI 2
    4.1 Surround output to Front, Rear and Subwoofer speakers
surround50:CARD=E26_1,DEV=0
    Emagic EMI 2
    5.0 Surround output to Front, Center and Rear speakers
surround51:CARD=E26_1,DEV=0
    Emagic EMI 2
    5.1 Surround output to Front, Center, Rear and Subwoofer speakers
surround71:CARD=E26_1,DEV=0
    Emagic EMI 2
    7.1 Surround output to Front, Center, Side, Rear and Woofer speakers
iec958:CARD=E26_1,DEV=0
    Emagic EMI 2
    IEC958 (S/PDIF) Digital Audio Output
dmix:CARD=E26_1,DEV=0
    Emagic EMI 2
    Direct sample mixing device
dsnoop:CARD=E26_1,DEV=0
    Emagic EMI 2
    Direct sample snooping device
hw:CARD=E26_1,DEV=0
    Emagic EMI 2
    Direct hardware device without any conversions
plughw:CARD=E26_1,DEV=0
    Emagic EMI 2
    Hardware device with all software conversions
dav@dav-ubu:~$ ac
accept                aclocal-1.15          acpi_listen
accessdb              aconnect              activity-log-manager
aclocal               acpi_available        acyclic
aclocal-1.11          acpid                 
dav@dav-ubu:~$ ac^C
dav@dav-ubu:~$ speaker-test -Dplug:merge -c 12 --rate 44100 --period 1024 -P 4

speaker-test 1.1.3

Playback device is plug:merge
Stream parameters are 44100Hz, S16_LE, 12 channels
Using 16 octaves of pink noise
Rate set to 44100Hz (requested 44100Hz)
Buffer size range from 90 to 87381
Period size range from 45 to 43690
Requested period time 1024 us
Periods = 4
was set period_size = 45
was set buffer_size = 180
 0 - Front Left
 6 - Front Left
^C 2 - Front Center
 8 - Front Center
 1 - Front Right
 7 - Front Right
 5 - Side Right
 11 - Side Right
 4 - Side Left
 10 - Side Left
 3 - LFE
 9 - LFE
Time per period = 5,730844
dav@dav-ubu:~$ speaker-test -Dhw:merge -c 12 --rate 44100 --period 1024 -P 4

speaker-test 1.1.3

Playback device is hw:merge
Stream parameters are 44100Hz, S16_LE, 12 channels
Using 16 octaves of pink noise
ALSA lib pcm_hw.c:1713:(_snd_pcm_hw_open) Invalid value for card
Playback open error: -19,No such device
dav@dav-ubu:~$ speaker-test -Dmulti:merge -c 12 --rate 44100 --period 1024 -P 4

speaker-test 1.1.3

Playback device is multi:merge
Stream parameters are 44100Hz, S16_LE, 12 channels
Using 16 octaves of pink noise
ALSA lib pcm.c:2495:(snd_pcm_open_noupdate) Unknown PCM multi:merge
Playback open error: -2,No such file or directory
dav@dav-ubu:~$ amixer 
Simple mixer control 'Master',0
  Capabilities: pvolume pvolume-joined pswitch pswitch-joined
  Playback channels: Mono
  Limits: Playback 0 - 87
  Mono: Playback 55 [63%] [-24.00dB] [on]
Simple mixer control 'Headphone',0
  Capabilities: pvolume pswitch
  Playback channels: Front Left - Front Right
  Limits: Playback 0 - 87
  Mono:
  Front Left: Playback 87 [100%] [0.00dB] [on]
  Front Right: Playback 87 [100%] [0.00dB] [on]
Simple mixer control 'Headphone',1
  Capabilities: pswitch
  Playback channels: Front Left - Front Right
  Mono:
  Front Left: Playback [on]
  Front Right: Playback [on]
Simple mixer control 'Speaker',0
  Capabilities: pvolume pswitch
  Playback channels: Front Left - Front Right
  Limits: Playback 0 - 87
  Mono:
  Front Left: Playback 0 [0%] [-65.25dB] [off]
  Front Right: Playback 0 [0%] [-65.25dB] [off]
Simple mixer control 'PCM',0
  Capabilities: pvolume
  Playback channels: Front Left - Front Right
  Limits: Playback 0 - 255
  Mono:
  Front Left: Playback 255 [100%] [0.00dB]
  Front Right: Playback 255 [100%] [0.00dB]
Simple mixer control 'Mic',0
  Capabilities: pvolume pswitch
  Playback channels: Front Left - Front Right
  Limits: Playback 0 - 31
  Mono:
  Front Left: Playback 0 [0%] [-34.50dB] [off]
  Front Right: Playback 0 [0%] [-34.50dB] [off]
Simple mixer control 'Mic Boost',0
  Capabilities: volume
  Playback channels: Front Left - Front Right
  Capture channels: Front Left - Front Right
  Limits: 0 - 3
  Front Left: 3 [100%] [30.00dB]
  Front Right: 3 [100%] [30.00dB]
Simple mixer control 'Beep',0
  Capabilities: pvolume pswitch
  Playback channels: Front Left - Front Right
  Limits: Playback 0 - 31
  Mono:
  Front Left: Playback 0 [0%] [-34.50dB] [off]
  Front Right: Playback 0 [0%] [-34.50dB] [off]
Simple mixer control 'Capture',0
  Capabilities: cvolume cswitch
  Capture channels: Front Left - Front Right
  Limits: Capture 0 - 63
  Front Left: Capture 63 [100%] [30.00dB] [off]
  Front Right: Capture 63 [100%] [30.00dB] [off]
Simple mixer control 'Auto-Mute Mode',0
  Capabilities: enum
  Items: 'Disabled' 'Enabled'
  Item0: 'Enabled'
Simple mixer control 'Digital',0
  Capabilities: cvolume
  Capture channels: Front Left - Front Right
  Limits: Capture 0 - 120
  Front Left: Capture 60 [50%] [0.00dB]
  Front Right: Capture 60 [50%] [0.00dB]
Simple mixer control 'Dock Mic',0
  Capabilities: pvolume pswitch
  Playback channels: Front Left - Front Right
  Limits: Playback 0 - 31
  Mono:
  Front Left: Playback 0 [0%] [-34.50dB] [off]
  Front Right: Playback 0 [0%] [-34.50dB] [off]
Simple mixer control 'Dock Mic Boost',0
  Capabilities: volume
  Playback channels: Front Left - Front Right
  Capture channels: Front Left - Front Right
  Limits: 0 - 3
  Front Left: 0 [0%] [0.00dB]
  Front Right: 0 [0%] [0.00dB]
Simple mixer control 'Internal Mic Boost',0
  Capabilities: volume
  Playback channels: Front Left - Front Right
  Capture channels: Front Left - Front Right
  Limits: 0 - 3
  Front Left: 0 [0%] [0.00dB]
  Front Right: 0 [0%] [0.00dB]
Simple mixer control 'Loopback Mixing',0
  Capabilities: enum
  Items: 'Disabled' 'Enabled'
  Item0: 'Disabled'
dav@dav-ubu:~$ alsa_out 
Jack: JackClient::SetupDriverSync driver sem in flush mode
Jack: JackLinuxFutex::Connect name = jack_sem.1000_default_alsa_out
Jack: Clock source : system clock via clock_gettime
Jack: JackLibClient::Open name = alsa_out refnum = 4
selected sample format: 32bit
Jack: JackClient::PortRegister ref = 4 name = alsa_out:playback_1 type = 32 bit float mono audio port_index = 7
Jack: JackClient::PortRegister ref = 4 name = alsa_out:playback_2 type = 32 bit float mono audio port_index = 8
Jack: JackClient::Activate
Jack: JackPosixThread::StartImp : create non RT thread
Jack: JackPosixThread::ThreadHandler : start
Jack: JackClient::kBufferSizeCallback buffer_size = 1024
Jack: JackClient::Init : period = 23219 computation = 100 constraint = 23219
Jack: JackPosixThread::AcquireRealTimeImp priority = 5
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 2
Jack: JackClient::kActivateClient name = alsa_out ref = 4 
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: WaitGraphChange...
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
^CJack: JackClient::Deactivate
Jack: JackClient::Deactivate res = 0
Jack: JackPosixThread::Kill
Jack: jack_client_close
Jack: JackClient::Close ref = 4
Jack: JackClient::Deactivate
Jack: JackSocketClientChannel::Stop
Jack: JackPosixThread::Kill
Jack: JackClientSocket::Close
Jack: JackClientSocket::Close
Jack: JackLibClient::~JackLibClient
Jack: JackShmReadWritePtr1::~JackShmReadWritePtr1 3
Jack: Succeeded in unlocking 422 byte memory area
Jack: JackLibGlobals Destroy c62e0310
Jack: ~JackLibGlobals
Jack: no message buffer overruns
Jack: JackPosixThread::Stop
Jack: JackPosixThread::ThreadHandler : exit
Jack: JackShmReadWritePtr::~JackShmReadWritePtr 1
Jack: Succeeded in unlocking 1187 byte memory area
Jack: JackShmReadWritePtr::~JackShmReadWritePtr 0
Jack: Succeeded in unlocking 82280346 byte memory area
Jack: jack_client_close res = 0
dav@dav-ubu:~$ speaker-test -Dmulti:merge -c 12 --rate 44100 --period 1024 -P 4^C
dav@dav-ubu:~$ speaker-test -Dhw:3,0 -c 6 --rate 44100 --period 1024 -P 4

speaker-test 1.1.3

Playback device is hw:3,0
Stream parameters are 44100Hz, S16_LE, 6 channels
Using 16 octaves of pink noise
Rate set to 44100Hz (requested 44100Hz)
Buffer size range from 90 to 87381
Period size range from 45 to 43690
Requested period time 1024 us
Periods = 4
was set period_size = 45
was set buffer_size = 180
 0 - Front Left
^C 2 - Front Center
 1 - Front Right
 5 - Side Right
 4 - Side Left
 3 - LFE
Time per period = 2,380860
dav@dav-ubu:~$ alsa_out -d hw:3,0 -c 6 -n 4 -p 1024 -r44100 -v -i
Jack: JackClient::SetupDriverSync driver sem in flush mode
Jack: JackLinuxFutex::Connect name = jack_sem.1000_default_alsa_out
Jack: Clock source : system clock via clock_gettime
Jack: JackLibClient::Open name = alsa_out refnum = 4
Unable to set hw params for playback: Broken pipe
Setting of hwparams failed: Broken pipe
dav@dav-ubu:~$ alsa_out -d hw:3,0 
Jack: JackClient::SetupDriverSync driver sem in flush mode
Jack: JackLinuxFutex::Connect name = jack_sem.1000_default_alsa_out
Jack: Clock source : system clock via clock_gettime
Jack: JackLibClient::Open name = alsa_out refnum = 4
Rate doesn't match (requested 44100Hz, get 96000Hz)
Setting of hwparams failed: Invalid argument
dav@dav-ubu:~$ alsa_out -d hw:3,0 
Jack: JackClient::SetupDriverSync driver sem in flush mode
Jack: JackLinuxFutex::Connect name = jack_sem.1000_default_alsa_out
Jack: Clock source : system clock via clock_gettime
Jack: JackLibClient::Open name = alsa_out refnum = 4
Rate doesn't match (requested 44100Hz, get 96000Hz)
Setting of hwparams failed: Invalid argument
dav@dav-ubu:~$ aplay -l
**** List of PLAYBACK Hardware Devices ****
card 0: PCH [HDA Intel PCH], device 0: ALC269VC Analog [ALC269VC Analog]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 1: NVidia [HDA NVidia], device 3: HDMI 0 [HDMI 0]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 1: NVidia [HDA NVidia], device 7: HDMI 1 [HDMI 1]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 1: NVidia [HDA NVidia], device 8: HDMI 2 [HDMI 2]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 2: E26 [Emagic EMI 2|6], device 0: USB Audio [USB Audio]
  Subdevices: 0/1
  Subdevice #0: subdevice #0
card 3: E26_1 [Emagic EMI 2|6], device 0: USB Audio [USB Audio]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
dav@dav-ubu:~$ speaker-test -Dhw:3,0 

speaker-test 1.1.3

Playback device is hw:3,0
Stream parameters are 48000Hz, S16_LE, 1 channels
Using 16 octaves of pink noise
Channels count (1) not available for playbacks: Invalid argument
Setting of hwparams failed: Invalid argument
dav@dav-ubu:~$ aplay -l
**** List of PLAYBACK Hardware Devices ****
card 0: PCH [HDA Intel PCH], device 0: ALC269VC Analog [ALC269VC Analog]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 1: NVidia [HDA NVidia], device 3: HDMI 0 [HDMI 0]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 1: NVidia [HDA NVidia], device 7: HDMI 1 [HDMI 1]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 1: NVidia [HDA NVidia], device 8: HDMI 2 [HDMI 2]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
card 2: E26 [Emagic EMI 2|6], device 0: USB Audio [USB Audio]
  Subdevices: 0/1
  Subdevice #0: subdevice #0
card 3: E26_1 [Emagic EMI 2|6], device 0: USB Audio [USB Audio]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
dav@dav-ubu:~$ aplay -L
null
    Discard all samples (playback) or generate zero samples (capture)
pulse
    PulseAudio Sound Server
merge
device
E26_2
E26_2_S16
    Angrezi: USB Card hw1 for Stream IN, 16 bit, dsnooped for mulitpl    e listeners
default:CARD=PCH
    HDA Intel PCH, ALC269VC Analog
    Default Audio Device
sysdefault:CARD=PCH
    HDA Intel PCH, ALC269VC Analog
    Default Audio Device
front:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    Front speakers
surround21:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    2.1 Surround output to Front and Subwoofer speakers
surround40:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    4.0 Surround output to Front and Rear speakers
surround41:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    4.1 Surround output to Front, Rear and Subwoofer speakers
surround50:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    5.0 Surround output to Front, Center and Rear speakers
surround51:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    5.1 Surround output to Front, Center, Rear and Subwoofer speakers
surround71:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    7.1 Surround output to Front, Center, Side, Rear and Woofer speakers
dmix:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    Direct sample mixing device
dsnoop:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    Direct sample snooping device
hw:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    Direct hardware device without any conversions
plughw:CARD=PCH,DEV=0
    HDA Intel PCH, ALC269VC Analog
    Hardware device with all software conversions
hdmi:CARD=NVidia,DEV=0
    HDA NVidia, HDMI 0
    HDMI Audio Output
hdmi:CARD=NVidia,DEV=1
    HDA NVidia, HDMI 1
    HDMI Audio Output
hdmi:CARD=NVidia,DEV=2
    HDA NVidia, HDMI 2
    HDMI Audio Output
dmix:CARD=NVidia,DEV=3
    HDA NVidia, HDMI 0
    Direct sample mixing device
dmix:CARD=NVidia,DEV=7
    HDA NVidia, HDMI 1
    Direct sample mixing device
dmix:CARD=NVidia,DEV=8
    HDA NVidia, HDMI 2
    Direct sample mixing device
dsnoop:CARD=NVidia,DEV=3
    HDA NVidia, HDMI 0
    Direct sample snooping device
dsnoop:CARD=NVidia,DEV=7
    HDA NVidia, HDMI 1
    Direct sample snooping device
dsnoop:CARD=NVidia,DEV=8
    HDA NVidia, HDMI 2
    Direct sample snooping device
hw:CARD=NVidia,DEV=3
    HDA NVidia, HDMI 0
    Direct hardware device without any conversions
hw:CARD=NVidia,DEV=7
    HDA NVidia, HDMI 1
    Direct hardware device without any conversions
hw:CARD=NVidia,DEV=8
    HDA NVidia, HDMI 2
    Direct hardware device without any conversions
plughw:CARD=NVidia,DEV=3
    HDA NVidia, HDMI 0
    Hardware device with all software conversions
plughw:CARD=NVidia,DEV=7
    HDA NVidia, HDMI 1
    Hardware device with all software conversions
plughw:CARD=NVidia,DEV=8
    HDA NVidia, HDMI 2
    Hardware device with all software conversions
default:CARD=E26
    Emagic EMI 2
    Default Audio Device
sysdefault:CARD=E26
    Emagic EMI 2
    Default Audio Device
front:CARD=E26,DEV=0
    Emagic EMI 2
    Front speakers
surround21:CARD=E26,DEV=0
    Emagic EMI 2
    2.1 Surround output to Front and Subwoofer speakers
surround40:CARD=E26,DEV=0
    Emagic EMI 2
    4.0 Surround output to Front and Rear speakers
surround41:CARD=E26,DEV=0
    Emagic EMI 2
    4.1 Surround output to Front, Rear and Subwoofer speakers
surround50:CARD=E26,DEV=0
    Emagic EMI 2
    5.0 Surround output to Front, Center and Rear speakers
surround51:CARD=E26,DEV=0
    Emagic EMI 2
    5.1 Surround output to Front, Center, Rear and Subwoofer speakers
surround71:CARD=E26,DEV=0
    Emagic EMI 2
    7.1 Surround output to Front, Center, Side, Rear and Woofer speakers
iec958:CARD=E26,DEV=0
    Emagic EMI 2
    IEC958 (S/PDIF) Digital Audio Output
dmix:CARD=E26,DEV=0
    Emagic EMI 2
    Direct sample mixing device
dsnoop:CARD=E26,DEV=0
    Emagic EMI 2
    Direct sample snooping device
hw:CARD=E26,DEV=0
    Emagic EMI 2
    Direct hardware device without any conversions
plughw:CARD=E26,DEV=0
    Emagic EMI 2
    Hardware device with all software conversions
default:CARD=E26_1
    Emagic EMI 2
    Default Audio Device
sysdefault:CARD=E26_1
    Emagic EMI 2
    Default Audio Device
front:CARD=E26_1,DEV=0
    Emagic EMI 2
    Front speakers
surround21:CARD=E26_1,DEV=0
    Emagic EMI 2
    2.1 Surround output to Front and Subwoofer speakers
surround40:CARD=E26_1,DEV=0
    Emagic EMI 2
    4.0 Surround output to Front and Rear speakers
surround41:CARD=E26_1,DEV=0
    Emagic EMI 2
    4.1 Surround output to Front, Rear and Subwoofer speakers
surround50:CARD=E26_1,DEV=0
    Emagic EMI 2
    5.0 Surround output to Front, Center and Rear speakers
surround51:CARD=E26_1,DEV=0
    Emagic EMI 2
    5.1 Surround output to Front, Center, Rear and Subwoofer speakers
surround71:CARD=E26_1,DEV=0
    Emagic EMI 2
    7.1 Surround output to Front, Center, Side, Rear and Woofer speakers
iec958:CARD=E26_1,DEV=0
    Emagic EMI 2
    IEC958 (S/PDIF) Digital Audio Output
dmix:CARD=E26_1,DEV=0
    Emagic EMI 2
    Direct sample mixing device
dsnoop:CARD=E26_1,DEV=0
    Emagic EMI 2
    Direct sample snooping device
hw:CARD=E26_1,DEV=0
    Emagic EMI 2
    Direct hardware device without any conversions
plughw:CARD=E26_1,DEV=0
    Emagic EMI 2
    Hardware device with all software conversions
dav@dav-ubu:~$ ^C
dav@dav-ubu:~$ speaker-test -Dplug:E26_2_S16

speaker-test 1.1.3

Playback device is plug:E26_2_S16
Stream parameters are 48000Hz, S16_LE, 1 channels
Using 16 octaves of pink noise
ALSA lib pcm_dsnoop.c:556:(snd_pcm_dsnoop_open) The dsnoop plugin supports only capture stream
Playback open error: -22,Invalid argument
dav@dav-ubu:~$ speaker-test -Dplug:E26_2_S16

speaker-test 1.1.3

Playback device is plug:E26_2_S16
Stream parameters are 48000Hz, S16_LE, 1 channels
Using 16 octaves of pink noise
ALSA lib pcm_dshare.c:684:(snd_pcm_dshare_open) dshare: specify bindings!!!
Playback open error: -22,Invalid argument
dav@dav-ubu:~$ speaker-test -Dplug:E26_3_S16

speaker-test 1.1.3

Playback device is plug:E26_3_S16
Stream parameters are 48000Hz, S16_LE, 1 channels
Using 16 octaves of pink noise
ALSA lib pcm.c:2495:(snd_pcm_open_noupdate) Unknown PCM E26_3_S16
Playback open error: -2,No such file or directory
dav@dav-ubu:~$ speaker-test -Dplug:complex_convert

speaker-test 1.1.3

Playback device is plug:complex_convert
Stream parameters are 48000Hz, S16_LE, 1 channels
Using 16 octaves of pink noise
Rate set to 48000Hz (requested 48000Hz)
Buffer size range from 97 to 95108
Period size range from 48 to 47554
Using max buffer size 95108
Periods = 4
was set period_size = 19021
was set buffer_size = 95108
 0 - Front Left
Time per period = 0,802351
 0 - Front Left
Time per period = 2,776833
 0 - Front Left
Time per period = 2,773824
 0 - Front Left
Time per period = 2,774068
 0 - Front Left
Time per period = 2,773902
 0 - Front Left
^CWrite error: -4,Interrupted system call
xrun_recovery failed: -4,Interrupted system call
Transfer failed: Interrupted system call
dav@dav-ubu:~$ alsa_out -d plug:complex_convert
Jack: JackClient::SetupDriverSync driver sem in flush mode
Jack: JackLinuxFutex::Connect name = jack_sem.1000_default_alsa_out
Jack: Clock source : system clock via clock_gettime
Jack: JackLibClient::Open name = alsa_out refnum = 4
selected sample format: float
Jack: JackClient::PortRegister ref = 4 name = alsa_out:playback_1 type = 32 bit float mono audio port_index = 7
Jack: JackClient::PortRegister ref = 4 name = alsa_out:playback_2 type = 32 bit float mono audio port_index = 8
Jack: JackClient::Activate
Jack: JackPosixThread::StartImp : create non RT thread
Jack: JackPosixThread::ThreadHandler : start
Jack: JackClient::kBufferSizeCallback buffer_size = 1024
Jack: JackClient::Init : period = 23219 computation = 100 constraint = 23219
Jack: JackPosixThread::AcquireRealTimeImp priority = 5
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 2
Jack: JackClient::kActivateClient name = alsa_out ref = 4 
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: WaitGraphChange...
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
^CJack: JackClient::Deactivate
Jack: JackClient::Deactivate res = 0
Jack: JackPosixThread::Kill
Jack: jack_client_close
Jack: JackClient::Close ref = 4
Jack: JackClient::Deactivate
Jack: JackSocketClientChannel::Stop
Jack: JackPosixThread::Kill
Jack: JackClientSocket::Close
Jack: JackClientSocket::Close
Jack: JackLibClient::~JackLibClient
Jack: JackShmReadWritePtr1::~JackShmReadWritePtr1 3
Jack: Succeeded in unlocking 422 byte memory area
Jack: JackLibGlobals Destroy 230ef310
Jack: ~JackLibGlobals
Jack: no message buffer overruns
Jack: JackPosixThread::Stop
Jack: JackPosixThread::ThreadHandler : exit
Jack: JackShmReadWritePtr::~JackShmReadWritePtr 1
Jack: Succeeded in unlocking 1187 byte memory area
Jack: JackShmReadWritePtr::~JackShmReadWritePtr 0
Jack: Succeeded in unlocking 82280346 byte memory area
Jack: jack_client_close res = 0
dav@dav-ubu:~$ speaker-test -Dplug:complex_convert

speaker-test 1.1.3

Playback device is plug:complex_convert
Stream parameters are 48000Hz, S16_LE, 1 channels
Using 16 octaves of pink noise
ALSA lib pcm_plug.c:1263:(_snd_pcm_plug_open) Unknown field channels
Playback open error: -22,Invalid argument
dav@dav-ubu:~$ speaker-test -Dplug:complex_convert -c 6

speaker-test 1.1.3

Playback device is plug:complex_convert
Stream parameters are 48000Hz, S16_LE, 6 channels
Using 16 octaves of pink noise
ALSA lib pcm_plug.c:1263:(_snd_pcm_plug_open) Unknown field channels
Playback open error: -22,Invalid argument
dav@dav-ubu:~$ alsa_out -d plug:complex_convert
Jack: JackClient::SetupDriverSync driver sem in flush mode
Jack: JackLinuxFutex::Connect name = jack_sem.1000_default_alsa_out
Jack: Clock source : system clock via clock_gettime
Jack: JackLibClient::Open name = alsa_out refnum = 4
ALSA lib pcm_plug.c:1263:(_snd_pcm_plug_open) Unknown field channels
Capture open error: Invalid argument
dav@dav-ubu:~$ alsa_out -d plug:complex_convert -c 6
Jack: JackClient::SetupDriverSync driver sem in flush mode
Jack: JackLinuxFutex::Connect name = jack_sem.1000_default_alsa_out
Jack: Clock source : system clock via clock_gettime
Jack: JackLibClient::Open name = alsa_out refnum = 4
ALSA lib pcm_plug.c:1263:(_snd_pcm_plug_open) Unknown field channels
Capture open error: Invalid argument
dav@dav-ubu:~$ ^C
dav@dav-ubu:~$ alsa_out -d plug:complex_convert
Jack: JackClient::SetupDriverSync driver sem in flush mode
Jack: JackLinuxFutex::Connect name = jack_sem.1000_default_alsa_out
Jack: Clock source : system clock via clock_gettime
Jack: JackLibClient::Open name = alsa_out refnum = 4
selected sample format: float
Jack: JackClient::PortRegister ref = 4 name = alsa_out:playback_1 type = 32 bit float mono audio port_index = 7
Jack: JackClient::PortRegister ref = 4 name = alsa_out:playback_2 type = 32 bit float mono audio port_index = 8
Jack: JackClient::Activate
Jack: JackPosixThread::StartImp : create non RT thread
Jack: JackPosixThread::ThreadHandler : start
Jack: JackClient::kBufferSizeCallback buffer_size = 1024
Jack: JackClient::Init : period = 23219 computation = 100 constraint = 23219
Jack: JackPosixThread::AcquireRealTimeImp priority = 5
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 2
Jack: JackClient::kActivateClient name = alsa_out ref = 4 
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: WaitGraphChange...
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
^CJack: JackClient::Deactivate
Jack: JackClient::Deactivate res = 0
Jack: JackPosixThread::Kill
Jack: jack_client_close
Jack: JackClient::Close ref = 4
Jack: JackClient::Deactivate
Jack: JackSocketClientChannel::Stop
Jack: JackPosixThread::Kill
Jack: JackClientSocket::Close
Jack: JackClientSocket::Close
Jack: JackLibClient::~JackLibClient
Jack: JackShmReadWritePtr1::~JackShmReadWritePtr1 3
Jack: Succeeded in unlocking 422 byte memory area
Jack: JackLibGlobals Destroy f5358310
Jack: ~JackLibGlobals
Jack: no message buffer overruns
Jack: JackPosixThread::Stop
Jack: JackPosixThread::ThreadHandler : exit
Jack: JackShmReadWritePtr::~JackShmReadWritePtr 1
Jack: Succeeded in unlocking 1187 byte memory area
Jack: JackShmReadWritePtr::~JackShmReadWritePtr 0
Jack: Succeeded in unlocking 82280346 byte memory area
Jack: jack_client_close res = 0
dav@dav-ubu:~$ speaker-test -Dplug:complex_convert

speaker-test 1.1.3

Playback device is plug:complex_convert
Stream parameters are 48000Hz, S16_LE, 1 channels
Using 16 octaves of pink noise
Rate set to 48000Hz (requested 48000Hz)
Buffer size range from 97 to 285326
Period size range from 48 to 142664
Using max buffer size 285324
Periods = 4
was set period_size = 57064
was set buffer_size = 285324
 0 - Front Left
Time per period = 2,615854
 0 - Front Left
Time per period = 2,627972
 0 - Front Left
^CTime per period = 0,689407
dav@dav-ubu:~$ speaker-test -Dplug:complex_convert

speaker-test 1.1.3

Playback device is plug:complex_convert
Stream parameters are 48000Hz, S16_LE, 1 channels
Using 16 octaves of pink noise
ALSA lib pcm.c:2495:(snd_pcm_open_noupdate) Unknown PCM E26_3_S16
Playback open error: -2,No such file or directory
dav@dav-ubu:~$ speaker-test -Dplug:complex_convert -c 2

speaker-test 1.1.3

Playback device is plug:complex_convert
Stream parameters are 48000Hz, S16_LE, 2 channels
Using 16 octaves of pink noise
ALSA lib pcm.c:2495:(snd_pcm_open_noupdate) Unknown PCM E26_3_S16
Playback open error: -2,No such file or directory
dav@dav-ubu:~$ speaker-test -Dplug:complex_convert

speaker-test 1.1.3

Playback device is plug:complex_convert
Stream parameters are 48000Hz, S16_LE, 1 channels
Using 16 octaves of pink noise
Rate set to 48000Hz (requested 48000Hz)
Buffer size range from 97 to 285326
Period size range from 48 to 142664
Using max buffer size 285324
Periods = 4
was set period_size = 57064
was set buffer_size = 285324
 0 - Front Left
^CTime per period = 2,154074
dav@dav-ubu:~$ speaker-test -Dplug:complex_convert

speaker-test 1.1.3

Playback device is plug:complex_convert
Stream parameters are 48000Hz, S16_LE, 1 channels
Using 16 octaves of pink noise
Rate set to 48000Hz (requested 48000Hz)
Buffer size range from 97 to 95108
Period size range from 48 to 47554
Using max buffer size 95108
Periods = 4
was set period_size = 19021
was set buffer_size = 95108
 0 - Front Left
Time per period = 0,809865
 0 - Front Left
^CWrite error: -4,Interrupted system call
xrun_recovery failed: -4,Interrupted system call
Transfer failed: Interrupted system call
dav@dav-ubu:~$ speaker-test -Dplug:complex_convert

speaker-test 1.1.3

Playback device is plug:complex_convert
Stream parameters are 48000Hz, S16_LE, 1 channels
Using 16 octaves of pink noise
ALSA lib pcm_plug.c:1263:(_snd_pcm_plug_open) Unknown field channels
Playback open error: -22,Invalid argument
dav@dav-ubu:~$ speaker-test -Dplug:complex_convert -c 6

speaker-test 1.1.3

Playback device is plug:complex_convert
Stream parameters are 48000Hz, S16_LE, 6 channels
Using 16 octaves of pink noise
ALSA lib pcm_plug.c:1263:(_snd_pcm_plug_open) Unknown field channels
Playback open error: -22,Invalid argument
dav@dav-ubu:~$ speaker-test -Dplug:complex_convert -c 2

speaker-test 1.1.3

Playback device is plug:complex_convert
Stream parameters are 48000Hz, S16_LE, 2 channels
Using 16 octaves of pink noise
ALSA lib pcm_plug.c:1263:(_snd_pcm_plug_open) Unknown field channels
Playback open error: -22,Invalid argument
dav@dav-ubu:~$ speaker-test -Dplug:complex_convert -c 0

speaker-test 1.1.3

Playback device is plug:complex_convert
Stream parameters are 48000Hz, S16_LE, 1 channels
Using 16 octaves of pink noise
ALSA lib pcm_plug.c:1263:(_snd_pcm_plug_open) Unknown field channels
Playback open error: -22,Invalid argument
dav@dav-ubu:~$ speaker-test -Dplug:complex_convert -c 2

speaker-test 1.1.3

Playback device is plug:complex_convert
Stream parameters are 48000Hz, S16_LE, 2 channels
Using 16 octaves of pink noise
Rate set to 48000Hz (requested 48000Hz)
Buffer size range from 97 to 285326
Period size range from 48 to 142664
Using max buffer size 285324
Periods = 4
was set period_size = 57064
was set buffer_size = 285324
 0 - Front Left
 1 - Front Right
Time per period = 5,265515
 0 - Front Left
^C 1 - Front Right
Time per period = 1,198318
dav@dav-ubu:~$ ^C
dav@dav-ubu:~$ speaker-test -Dplug:complex_convert -c 6

speaker-test 1.1.3

Playback device is plug:complex_convert
Stream parameters are 48000Hz, S16_LE, 6 channels
Using 16 octaves of pink noise
Rate set to 48000Hz (requested 48000Hz)
Buffer size range from 97 to 95108
Period size range from 48 to 47554
Using max buffer size 95108
Periods = 4
was set period_size = 19021
was set buffer_size = 95108
 0 - Front Left
 2 - Front Center
 1 - Front Right
^CWrite error: -4,Interrupted system call
xrun_recovery failed: -4,Interrupted system call
Transfer failed: Interrupted system call
dav@dav-ubu:~$ alsa_out -d plug:complex_convert -c 6
Jack: JackClient::SetupDriverSync driver sem in flush mode
Jack: JackLinuxFutex::Connect name = jack_sem.1000_default_alsa_out
Jack: Clock source : system clock via clock_gettime
Jack: JackLibClient::Open name = alsa_out refnum = 4
selected sample format: float
Jack: JackClient::PortRegister ref = 4 name = alsa_out:playback_1 type = 32 bit float mono audio port_index = 7
Jack: JackClient::PortRegister ref = 4 name = alsa_out:playback_2 type = 32 bit float mono audio port_index = 8
Jack: JackClient::PortRegister ref = 4 name = alsa_out:playback_3 type = 32 bit float mono audio port_index = 9
Jack: JackClient::PortRegister ref = 4 name = alsa_out:playback_4 type = 32 bit float mono audio port_index = 10
Jack: JackClient::PortRegister ref = 4 name = alsa_out:playback_5 type = 32 bit float mono audio port_index = 11
Jack: JackClient::PortRegister ref = 4 name = alsa_out:playback_6 type = 32 bit float mono audio port_index = 12
Jack: JackClient::Activate
Jack: JackPosixThread::StartImp : create non RT thread
Jack: JackPosixThread::ThreadHandler : start
Jack: JackClient::kBufferSizeCallback buffer_size = 1024
Jack: JackClient::Init : period = 23219 computation = 100 constraint = 23219
Jack: JackPosixThread::AcquireRealTimeImp priority = 5
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 2
Jack: JackClient::kActivateClient name = alsa_out ref = 4 
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: WaitGraphChange...
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 5 name = pure_data_0 notify = 0
Jack: JackClient::AddClient name = pure_data_0, ref = 5 
Jack: JackLinuxFutex::Connect name = jack_sem.1000_default_pure_data_0
Jack: JackClient::kAddClient fName = alsa_out name = pure_data_0
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: WaitGraphChange...
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 3 name = qjackctl notify = 1
Jack: JackClient::RemoveClient name = qjackctl, ref = 3 
Jack: JackClient::kRemoveClient fName = alsa_out name = qjackctl
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 2 name = dbusapi notify = 1
Jack: JackClient::RemoveClient name = dbusapi, ref = 2 
Jack: JackClient::kRemoveClient fName = alsa_out name = dbusapi
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 18
Jack: JackClient::ClientNotify ref = 4 name = alsa_out notify = 15
Jack: JackClient::kShutDownCallback
Jack: JackLibClient::ShutDown
Jack: JackClient::ShutDown
dav@dav-ubu:~$ ^C
dav@dav-ubu:~$ sudo pm-hibernate
[sudo] password for dav: 
dav@dav-ubu:~$ xrandr
Screen 0: minimum 320 x 200, current 3200 x 1080, maximum 8192 x 8192
LVDS-1 connected primary 1920x1080+1280+0 (normal left inverted right x axis y axis) 344mm x 193mm
   1920x1080     60.02 +  60.01*   59.97    59.96    50.03    59.93  
   1680x1050     59.95    59.88  
   1600x1024     60.17  
   1400x1050     59.98  
   1600x900      59.99    59.94    59.95    59.82  
   1280x1024     60.02  
   1440x900      59.89  
   1400x900      59.96    59.88  
   1280x960      60.00  
   1440x810      60.00    59.97  
   1368x768      59.88    59.85  
   1360x768      59.80    59.96  
   1280x800      59.99    59.97    59.81    59.91  
   1152x864      60.00  
   1280x720      60.00    59.99    59.86    59.74  
   1024x768      60.04    60.00  
   960x720       60.00  
   928x696       60.05  
   896x672       60.01  
   1024x576      59.95    59.96    59.90    59.82  
   960x600       59.93    60.00  
   960x540       59.96    59.99    59.63    59.82  
   800x600       60.00    60.32    56.25  
   840x525       60.01    59.88  
   864x486       59.92    59.57  
   800x512       60.17  
   700x525       59.98  
   800x450       59.95    59.82  
   640x512       60.02  
   720x450       59.89  
   700x450       59.96    59.88  
   640x480       60.00    59.94  
   720x405       59.51    58.99  
   684x384       59.88    59.85  
   680x384       59.80    59.96  
   640x400       59.88    59.98  
   576x432       60.06  
   640x360       59.86    59.83    59.84    59.32  
   512x384       60.00  
   512x288       60.00    59.92  
   480x270       59.63    59.82  
   400x300       60.32    56.34  
   432x243       59.92    59.57  
   320x240       60.05  
   360x202       59.51    59.13  
   320x180       59.84    59.32  
VGA-1 disconnected (normal left inverted right x axis y axis)
LVDS-1-2 disconnected (normal left inverted right x axis y axis)
VGA-1-2 disconnected 1280x1024+0+0 (normal left inverted right x axis y axis) 0mm x 0mm
DP-1-1 disconnected (normal left inverted right x axis y axis)
DP-1-2 disconnected (normal left inverted right x axis y axis)
DP-1-3 disconnected (normal left inverted right x axis y axis)
  1280x1024 (0x25f) 141.813MHz -HSync +VSync
        h: width  1280 start 1376 end 1512 total 1744 skew    0 clock  81.31KHz
        v: height 1024 start 1025 end 1028 total 1070           clock  76.00Hz
dav@dav-ubu:~$ sudo pm-hibernate
[sudo] password for dav: 
dav@dav-ubu:~$ xrandr
Screen 0: minimum 320 x 200, current 1920 x 1080, maximum 8192 x 8192
LVDS-1 connected primary 1920x1080+0+0 (normal left inverted right x axis y axis) 344mm x 193mm
   1920x1080     60.02*+  60.01    59.97    59.96    50.03    59.93  
   1680x1050     59.95    59.88  
   1600x1024     60.17  
   1400x1050     59.98  
   1600x900      59.99    59.94    59.95    59.82  
   1280x1024     60.02  
   1440x900      59.89  
   1400x900      59.96    59.88  
   1280x960      60.00  
   1440x810      60.00    59.97  
   1368x768      59.88    59.85  
   1360x768      59.80    59.96  
   1280x800      59.99    59.97    59.81    59.91  
   1152x864      60.00  
   1280x720      60.00    59.99    59.86    59.74  
   1024x768      60.04    60.00  
   960x720       60.00  
   928x696       60.05  
   896x672       60.01  
   1024x576      59.95    59.96    59.90    59.82  
   960x600       59.93    60.00  
   960x540       59.96    59.99    59.63    59.82  
   800x600       60.00    60.32    56.25  
   840x525       60.01    59.88  
   864x486       59.92    59.57  
   800x512       60.17  
   700x525       59.98  
   800x450       59.95    59.82  
   640x512       60.02  
   720x450       59.89  
   700x450       59.96    59.88  
   640x480       60.00    59.94  
   720x405       59.51    58.99  
   684x384       59.88    59.85  
   680x384       59.80    59.96  
   640x400       59.88    59.98  
   576x432       60.06  
   640x360       59.86    59.83    59.84    59.32  
   512x384       60.00  
   512x288       60.00    59.92  
   480x270       59.63    59.82  
   400x300       60.32    56.34  
   432x243       59.92    59.57  
   320x240       60.05  
   360x202       59.51    59.13  
   320x180       59.84    59.32  
VGA-1 disconnected (normal left inverted right x axis y axis)
LVDS-1-2 disconnected (normal left inverted right x axis y axis)
VGA-1-2 connected (normal left inverted right x axis y axis)
   1680x1050     59.95 +
   1280x1024     75.02    60.02  
   1152x864      75.00  
   1024x768      75.03    60.00  
   800x600       75.00    60.32  
   640x480       75.00    59.94  
   720x400       70.08  
DP-1-1 disconnected (normal left inverted right x axis y axis)
DP-1-2 disconnected (normal left inverted right x axis y axis)
DP-1-3 disconnected (normal left inverted right x axis y axis)
  1680x1050 (0x74) 146.250MHz -HSync +VSync
        h: width  1680 start 1784 end 1960 total 2240 skew    0 clock  65.29KHz
        v: height 1050 start 1053 end 1059 total 1089           clock  59.95Hz
  1280x1024 (0x7c) 108.000MHz +HSync +VSync
        h: width  1280 start 1328 end 1440 total 1688 skew    0 clock  63.98KHz
        v: height 1024 start 1025 end 1028 total 1066           clock  60.02Hz
  1024x768 (0x91) 65.000MHz -HSync -VSync
        h: width  1024 start 1048 end 1184 total 1344 skew    0 clock  48.36KHz
        v: height  768 start  771 end  777 total  806           clock  60.00Hz
  800x600 (0xa0) 40.000MHz +HSync +VSync
        h: width   800 start  840 end  968 total 1056 skew    0 clock  37.88KHz
        v: height  600 start  601 end  605 total  628           clock  60.32Hz
  640x480 (0xaf) 25.175MHz -HSync -VSync
        h: width   640 start  656 end  752 total  800 skew    0 clock  31.47KHz
        v: height  480 start  490 end  492 total  525           clock  59.94Hz
dav@dav-ubu:~$ pcm_slave.E26_3_S16 {
pcm_slave.E26_3_S16 {
        pcm "hw:3,0"
        format S16_LE
        rate 44100
	channels 6
	
}

pcm.complex_convert {
        type plug
        slave E26_3_S16
}pcm_slave.E26_3_S16: command not found
dav@dav-ubu:~$         pcm "hw:3,0"

Command 'pcm' not found, but there are 21 similar ones.

dav@dav-ubu:~$         format S16_LE

Command 'format' not found, did you mean:

  command 'mformat' from deb mtools
  command 'hformat' from deb hfsutils

Try: sudo apt install <deb name>

dav@dav-ubu:~$         rate 44100

Command 'rate' not found, did you mean:

  command 'rake' from snap ruby (2.7.0)
  command 'kate' from snap kate (19.08.0)
  command 'rdate' from deb rdate
  command 'late' from deb late
  command 'rake' from deb rake
  command 'kate' from deb kate
  command 'date' from deb coreutils
  command 'ratt' from deb ratt

See 'snap info <snapname>' for additional versions.

dav@dav-ubu:~$ channels 6
channels: command not found
dav@dav-ubu:~$ 
dav@dav-ubu:~$ }
bash: syntax error near unexpected token `}'
dav@dav-ubu:~$ 
dav@dav-ubu:~$ pcm.complex_convert {
pcm.complex_convert: command not found
dav@dav-ubu:~$         type plug
bash: type: plug: not found
dav@dav-ubu:~$         slave E26_3_S16

Command 'slave' not found, did you mean:

  command 'save' from deb atfs
  command 'slave1' from deb pvm-examples

Try: sudo apt install <deb name>

dav@dav-ubu:~$ }pcm_slave.E26_3_S16 {
}pcm_slave.E26_3_S16: command not found
dav@dav-ubu:~$         pcm "hw:3,0"

Command 'pcm' not found, but there are 21 similar ones.

dav@dav-ubu:~$         format S16_LE

Command 'format' not found, did you mean:

  command 'mformat' from deb mtools
  command 'hformat' from deb hfsutils

Try: sudo apt install <deb name>

dav@dav-ubu:~$         rate 44100

Command 'rate' not found, did you mean:

  command 'rake' from snap ruby (2.7.0)
  command 'kate' from snap kate (19.08.0)
  command 'rdate' from deb rdate
  command 'late' from deb late
  command 'kate' from deb kate
  command 'ratt' from deb ratt
  command 'rake' from deb rake
  command 'date' from deb coreutils

See 'snap info <snapname>' for additional versions.

dav@dav-ubu:~$ channels 6
channels: command not found
dav@dav-ubu:~$ 
dav@dav-ubu:~$ }
bash: syntax error near unexpected token `}'
dav@dav-ubu:~$ 
dav@dav-ubu:~$ pcm.complex_convert {
pcm.complex_convert: command not found
dav@dav-ubu:~$         type plug
bash: type: plug: not found
dav@dav-ubu:~$         slave E26_3_S16

Command 'slave' not found, did you mean:

  command 'slave1' from deb pvm-examples
  command 'save' from deb atfs

Try: sudo apt install <deb name>

dav@dav-ubu:~$ }

```
