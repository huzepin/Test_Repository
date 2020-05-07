
#ifndef OSDAPI_H
#define OSDAPI_H

#ifdef __cplusplus
extern "C" {
#endif


int exitWfSDK();
int initWfSDK(char *szcIP, unsigned int uiPort);
int WFOSD_setOsdDisplayLineNum(unsigned int uiLineNum);
int WFOSD_set(char *szcIP, unsigned int uiPort, char *szcOsd, int iOsdLen, int iX, int iY, unsigned short iColor, char *szcCode);
int WFOSD_clear(char *szcIP, unsigned int uiPort, char *szcCode);

#ifdef __cplusplus
}
#endif

#endif

