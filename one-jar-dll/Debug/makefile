################################################################################
# Automatically-generated file. Do not edit!
################################################################################

ROOT := ..

-include $(ROOT)/makefile.init

RM := rm -rf

# All of the sources participating in the build are defined here
-include sources.mk
-include $(SUBDIRS:%=%/subdir.mk)
-include objects.mk
-include $(DEPS)
-include $(ROOT)/makefile.defs

all: one-jar-dll.dll

one-jar-dll.dll: $(OBJS)
	@echo 'Building target: $@'
	g++ -Wl,--kill-at -shared -o $@ $(OBJS) $(USER_OBJS) $(LIBS)
	@echo 'Finished building: $@'

clean:
	-$(RM) $(OBJS) $(DEPS) one-jar-dll.dll

.PHONY: all clean dependents

-include $(ROOT)/makefile.targets
