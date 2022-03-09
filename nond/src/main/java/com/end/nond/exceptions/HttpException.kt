package com.end.nond.exceptions

import okhttp3.Response

class HttpException(response: Response) : Exception("HTTP ${response.code}: ${response.message}")