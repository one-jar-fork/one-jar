################################################################################
# Automatically-generated file. Do not edit!
################################################################################

S_UPPER_SRCS += \
${addprefix $(ROOT)/cpp/, \
}

CPP_SRCS += \
${addprefix $(ROOT)/cpp/, \
Test.cpp \
}

CC_SRCS += \
${addprefix $(ROOT)/cpp/, \
}

C_SRCS += \
${addprefix $(ROOT)/cpp/, \
}

C_UPPER_SRCS += \
${addprefix $(ROOT)/cpp/, \
}

CXX_SRCS += \
${addprefix $(ROOT)/cpp/, \
}

S_SRCS += \
${addprefix $(ROOT)/cpp/, \
}

# Each subdirectory must supply rules for building sources it contributes
cpp/%.o: $(ROOT)/cpp/%.cpp
	@echo 'Building file: $<'
	@echo g++ -I"C:\j2sdk1.4.2_04\include" -I"C:\j2sdk1.4.2_04\include\win32" -O0 -g3 -Wall -c -fmessage-length=0 -o$@ $<
	@g++ -I"C:\j2sdk1.4.2_04\include" -I"C:\j2sdk1.4.2_04\include\win32" -O0 -g3 -Wall -c -fmessage-length=0 -o$@ $< && \
	echo -n $(@:%.o=%.d) $(dir $@) > $(@:%.o=%.d) && \
	g++ -MM -MG -P -w -I"C:\j2sdk1.4.2_04\include" -I"C:\j2sdk1.4.2_04\include\win32" -O0 -g3 -Wall -c -fmessage-length=0  $< >> $(@:%.o=%.d)
	@echo 'Finished building: $<'
	@echo ' '


