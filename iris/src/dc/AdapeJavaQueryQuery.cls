Class iris.src.dc.AdapterPerfmonProc Extends %RegisteredObject
{

ClassMethod start() As %Status
{
    Set namespace = $NAMESPACE
    zn "%SYS"
    set status = $$Stop^PERFMON()
    set status = $$Start^PERFMON()
    zn namespace
    
    return status
}

ClassMethod generateReport(nameReport As %String = "report.txt") As %Status
{
    Set namespace = $NAMESPACE
    zn "%SYS"
    Set tempDirectory = ##class(%SYS.System).TempDirectory()
    set status = $$Report^PERFMON("R","R","P", tempDirectory_"/"_nameReport)
    zn namespace

    return status
}

ClassMethod stop() As %Status
{
    Set namespace = $NAMESPACE
    zn "%SYS"
    Set status = $$Stop^PERFMON()
    zn namespace

    return status
}

}
